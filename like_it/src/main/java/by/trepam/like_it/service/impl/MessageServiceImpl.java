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

	public Message getMessage(Integer id) throws ServiceException {
		Message message = null;
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			MessageDAO mesdao = df.getMessageDAO();
			message = mesdao.getMessage(id);
			AnswerDAO ansdao = df.getAnswerDAO();
			AccountDAO acdao = df.getAccountDAO();
			ImageDAO imgdao = df.getImageDAO();
			List<Answer> answers = ansdao.getAllAnswersOfMessage(id);
			Account ac;
			for(Answer a:answers){
				ac=acdao.getAccount(a.getAuthor().getId());				
				ac.setPhoto(imgdao.getImage(ac.getPhoto().getId()));
				ac.setRating(acdao.rating(ac.getId()));
				a.setAuthor(ac);
				a.setRating(ansdao.rating(a.getId()));
			}
			message.setAnswers(answers);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during getting message",e);
		}
		return message;
	}

	public void addMessage(Message message, int id_category) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			MessageDAO mesdao = df.getMessageDAO();
			mesdao.insert(message,id_category);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}		
	}

	public void deleteMessage(int id_message) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			MessageDAO mesdao = df.getMessageDAO();
			mesdao.delete(id_message);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
	}

	public void updateMessage(Message message) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			MessageDAO mesdao = df.getMessageDAO();
			mesdao.update(message);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}	
		
	}

}
