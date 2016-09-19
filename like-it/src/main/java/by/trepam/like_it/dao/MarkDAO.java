package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Mark;

/**
 * Interface class of data access layer. It provides access to answers' marks
 * data.
 *
 */

public interface MarkDAO {

	/**
	 * Method inserts new mark data of answer with answerId in database.
	 * 
	 * @param mark
	 * @param answerId
	 * @throws DAOException
	 */
	void insert(Mark mark, Integer answerId) throws DAOException;

	/**
	 * Method deletes mark data, if mark of user authorId to answer with
	 * answerId exists.
	 * 
	 * @param authorId
	 * @param answerId
	 * @throws DAOException
	 */
	void delete(Integer authorId, Integer answerId) throws DAOException;

	/**
	 * Method returns list of all existing marks of answer with answerId.
	 * 
	 * @param ancwerId
	 * @return
	 * @throws DAOException
	 */
	List<Mark> getAllMarksOfAnswer(Integer ancwerId) throws DAOException;

	/**
	 * Method updates mark data, if this mark of answer with answerId exists.
	 * 
	 * @param mark
	 * @param answerId
	 * @throws DAOException
	 */
	void update(Mark mark, Integer answerId) throws DAOException;
}
