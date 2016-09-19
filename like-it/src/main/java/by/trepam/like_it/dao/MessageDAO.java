package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Message;

/**
 * Interface class of data access layer. It provides access to messages data.
 *
 */

public interface MessageDAO {

	/**
	 * Method inserts new message data of category with categoryId in database.
	 * 
	 * @param message
	 * @param categoryId
	 * @throws DAOException
	 */
	void insert(Message message, Integer categoryId) throws DAOException;

	/**
	 * Method deletes message data, if message with messageId exists.
	 * 
	 * @param messageId
	 * @throws DAOException
	 */
	void delete(Integer messageId) throws DAOException;

	/**
	 * Method returns existing message or null, if message with messageId does
	 * not exist.
	 * 
	 * @param messageId
	 * @return
	 * @throws DAOException
	 */
	Message getMessage(Integer messageId) throws DAOException;

	/**
	 * Method returns list of all existing messages of category with categoryId.
	 * 
	 * @param categoryId
	 * @return
	 * @throws DAOException
	 */
	List<Message> getAllMessagesOfCategory(Integer categoryId) throws DAOException;

	/**
	 * Method updates message data, if this message exists.
	 * 
	 * @param message
	 * @throws DAOException
	 */
	void update(Message message) throws DAOException;
}
