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
	private static final AnswerServiceImpl service = new AnswerServiceImpl();
	private static final DAOFactory daoFactory = PostgresqlDAOFactory.getInstance();
	
	private AnswerServiceImpl(){}
	
	public static AnswerServiceImpl getInstance(){
		return service;
	}	

	public void rating(Mark mark, Integer answerID) throws ServiceException {
		try {
			
			AnswerDAO ansdao = daoFactory.getAnswerDAO();
			MarkDAO markdao = daoFactory.getMarkDAO();
			Answer answer = ansdao.getAnswer(answerID);
			if(mark.getAuthor().getId()!=answer.getAuthor().getId()){
				markdao.delete(mark.getAuthor().getId(), answerID);
				markdao.insert(mark, answerID);
			}
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during rating",e);
		}
	}

	public void addAnswer(Answer answer,Integer messageID) throws ServiceException {
		try {
			
			AnswerDAO ansdao = daoFactory.getAnswerDAO();
			ansdao.insert(answer, messageID);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding answer",e);
		}
		
	}

}
