package by.trepam.like_it.service.impl;

import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.MarkDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.DAOFactory;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Mark;
import by.trepam.like_it.service.AnswerService;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

public class AnswerServiceImpl implements AnswerService {
	private static final AnswerServiceImpl service = new AnswerServiceImpl();
	private static final DAOFactory daoFactory = PostgresqlDAOFactory.getInstance();

	private AnswerServiceImpl() {
	}

	public static AnswerServiceImpl getInstance() {
		return service;
	}

	public void rating(Mark mark, Integer answerId)
			throws GettingDataException, WrongDataException, DataNotFoundException {
		try {
			if (mark == null || answerId == null) {
				throw new WrongDataException("Wrong rating data");
			}
			AnswerDAO ansdao = daoFactory.getAnswerDAO();
			Answer answer = ansdao.getAnswer(answerId);
			if (answer == null) {
				throw new DataNotFoundException("Answer wasn't found");
			}
			if (mark.getAuthor().getId() != answer.getAuthor().getId()) {
				MarkDAO markdao = daoFactory.getMarkDAO();
				markdao.delete(mark.getAuthor().getId(), answerId);
				markdao.insert(mark, answerId);
			}
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during rating", e);
		}
	}

	public void addAnswer(Answer answer, Integer messageId) throws GettingDataException, WrongDataException {
		try {
			if (answer == null || messageId == null) {
				throw new WrongDataException("Wrong data");
			}
			AnswerDAO ansdao = daoFactory.getAnswerDAO();
			ansdao.insert(answer, messageId);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during adding answer", e);
		}

	}

}
