package by.trepam.like_it.service.impl;

import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.MarkDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.DAOFactory;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Mark;
import by.trepam.like_it.service.AnswerService;
import by.trepam.like_it.service.exception.ServiceException;

public class AnswerServiceImpl implements AnswerService {

	public void rating(Mark mark, int answerID) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			AnswerDAO ansdao = df.getAnswerDAO();
			MarkDAO markdao = df.getMarkDAO();
			Answer answer = ansdao.getAnswer(answerID);
			if(mark.getAuthor().getId()!=answer.getAuthor().getId()){
				markdao.delete(mark.getAuthor().getId(), answerID);
				markdao.insert(mark, answerID);
			}
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during rating",e);
		}
	}

	public void addAnswer(Answer answer,int messageID) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			AnswerDAO ansdao = df.getAnswerDAO();
			ansdao.insert(answer, messageID);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding answer",e);
		}
		
	}

}
