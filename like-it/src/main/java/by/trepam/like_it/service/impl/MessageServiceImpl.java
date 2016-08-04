package by.trepam.like_it.service.impl;

import java.util.List;

import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.MessageDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.DAOFactory;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.MessageService;
import by.trepam.like_it.service.exception.ServiceException;

public class MessageServiceImpl implements MessageService {
	private static final MessageServiceImpl service = new MessageServiceImpl();
	private static final DAOFactory daoFactory = PostgresqlDAOFactory.getInstance();

	private MessageServiceImpl() {
	}

	public static MessageServiceImpl getInstance() {
		return service;
	}

	public Message getMessage(Integer id) throws ServiceException {
		Message message = null;
		try {
			MessageDAO mesdao = daoFactory.getMessageDAO();
			message = mesdao.getMessage(id);
			AnswerDAO ansdao = daoFactory.getAnswerDAO();
			AccountDAO acdao = daoFactory.getAccountDAO();
			ImageDAO imgdao = daoFactory.getImageDAO();
			List<Answer> answers = ansdao.getAllAnswersOfMessage(id);
			Account account;
			for (Answer answer : answers) {
				account = acdao.getAccount(answer.getAuthor().getId());
				account.setPhoto(imgdao.getImage(account.getPhoto().getId()));
				account.setRating(acdao.rating(account.getId()));
				answer.setAuthor(account);
				answer.setRating(ansdao.rating(answer.getId()));
			}
			message.setAnswers(answers);
			account = acdao.getAccount(message.getAuthor().getId());
			account.setPhoto(imgdao.getImage(account.getPhoto().getId()));
			account.setRating(acdao.rating(account.getId()));
			message.setAuthor(account);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during getting message", e);
		}
		return message;
	}

	public void addMessage(Message message, Integer id_category) throws ServiceException {
		try {
			MessageDAO mesdao = daoFactory.getMessageDAO();
			mesdao.insert(message, id_category);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message", e);
		}
	}

	public void deleteMessage(Integer id_message) throws ServiceException {
		try {
			MessageDAO mesdao = daoFactory.getMessageDAO();
			mesdao.delete(id_message);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message", e);
		}
	}

	public void updateMessage(Message message) throws ServiceException {
		try {
			MessageDAO mesdao = daoFactory.getMessageDAO();
			mesdao.update(message);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message", e);
		}

	}

}
