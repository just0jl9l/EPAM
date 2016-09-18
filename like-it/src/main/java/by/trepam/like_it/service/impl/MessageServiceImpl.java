package by.trepam.like_it.service.impl;

import java.util.List;

import by.trepam.like_it.command.impl.CommandConstant;
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
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

public class MessageServiceImpl implements MessageService {
	private static final MessageServiceImpl service = new MessageServiceImpl();
	private static final DAOFactory daoFactory = PostgresqlDAOFactory.getInstance();

	private MessageServiceImpl() {
	}

	public static MessageServiceImpl getInstance() {
		return service;
	}

	public Message getMessage(Integer messageId)
			throws WrongDataException, GettingDataException, DataNotFoundException {
		try {
			Message message = null;
			if (messageId == null) {
				throw new WrongDataException("Wrong message ID");
			}
			MessageDAO mesdao = daoFactory.getMessageDAO();
			message = mesdao.getMessage(messageId);
			if (message == null) {
				throw new DataNotFoundException("Message wasn't found");
			}
			AnswerDAO ansdao = daoFactory.getAnswerDAO();
			List<Answer> answers = ansdao.getAllAnswersOfMessage(messageId);
			if (answers == null) {
				throw new DataNotFoundException("Answers weren't found");
			}
			Account account;
			AccountDAO acdao = daoFactory.getAccountDAO();
			ImageDAO imgdao = daoFactory.getImageDAO();
			for (Answer answer : answers) {
				account = acdao.getAccount(answer.getAuthor().getId());
				if (account == null) {
					throw new DataNotFoundException("Author wasn't found");
				}
				account.setPhoto(imgdao.getImage(account.getPhoto().getId()));
				account.setRating(acdao.rating(account.getId()));
				answer.setAuthor(account);
				answer.setRating(ansdao.rating(answer.getId()));
			}
			message.setAnswers(answers);
			account = acdao.getAccount(message.getAuthor().getId());
			if (account == null) {
				throw new DataNotFoundException("Author wasn't found");
			}
			account.setPhoto(imgdao.getImage(account.getPhoto().getId()));
			account.setRating(acdao.rating(account.getId()));
			message.setAuthor(account);
			return message;
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during getting message", e);
		}
	}

	public void addMessage(Message message, Integer categoryId) throws GettingDataException, WrongDataException {
		try {
			if (message == null) {
				throw new WrongDataException("Wrong message");
			}
			MessageDAO mesdao = daoFactory.getMessageDAO();
			mesdao.insert(message, categoryId);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during adding message", e);
		}
	}

	public void deleteMessage(Integer messageId) throws GettingDataException, WrongDataException {
		try {
			if (messageId == null) {
				throw new WrongDataException("Wrong message ID");
			}
			MessageDAO mesdao = daoFactory.getMessageDAO();
			mesdao.delete(messageId);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during adding message", e);
		}
	}

	public void updateMessage(Message message, String title, String text)
			throws GettingDataException, WrongDataException {
		try {
			if (message == null || title == null || text == null || CommandConstant.EMPTY.equals(title)
					|| CommandConstant.EMPTY.equals(text)) {
				throw new WrongDataException("Wrong data");
			}
			message.setName(title);
			message.setText(text);
			MessageDAO mesdao = daoFactory.getMessageDAO();
			mesdao.update(message);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during adding message", e);
		}

	}

}
