package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Answer;

/**
 * Interface class of data access layer. It provides access to answers data.
 *
 */

public interface AnswerDAO {

	/**
	 * Method inserts new answer data in database.
	 * 
	 * @param answer
	 * @param messageId
	 * @throws DAOException
	 */
	void insert(Answer answer, Integer messageId) throws DAOException;

	/**
	 * Method deletes answer data, if answer with answerId exists.
	 * 
	 * @param answerId
	 * @throws DAOException
	 */
	void delete(Integer answerId) throws DAOException;

	/**
	 * Method returns existing answer or null, if answer with answerId does not
	 * exist.
	 * 
	 * @param answerId
	 * @return
	 * @throws DAOException
	 */
	Answer getAnswer(Integer answerId) throws DAOException;

	/**
	 * Method returns answers list of existing message or empty list, if message
	 * with messageId does not exist.
	 * 
	 * @param messageId
	 * @return
	 * @throws DAOException
	 */
	List<Answer> getAllAnswersOfMessage(Integer messageId) throws DAOException;

	/**
	 * Method updates answer data, if answer of message with messageId exists.
	 * 
	 * @param answer
	 * @param messageId
	 * @throws DAOException
	 */
	void update(Answer answer, Integer messageId) throws DAOException;

	/**
	 * Method returns answer rating or 0.0, if marks of answer with answerId do
	 * not exist.
	 * 
	 * @param answerId
	 * @return
	 * @throws DAOException
	 */
	Double rating(Integer answerId) throws DAOException;
}
