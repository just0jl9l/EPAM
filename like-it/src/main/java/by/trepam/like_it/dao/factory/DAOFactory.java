package by.trepam.like_it.dao.factory;

import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.CategoryDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.MarkDAO;
import by.trepam.like_it.dao.MessageDAO;

/**
 * Interface class. It provides access to data access objects.
 *
 */

public interface DAOFactory {

	/**
	 * Method returns data access object, that provides access to accounts data.
	 * 
	 * @return
	 */
	AccountDAO getAccountDAO();

	/**
	 * Method returns data access object, that provides access to answers data.
	 * 
	 * @return
	 */
	AnswerDAO getAnswerDAO();

	/**
	 * Method returns data access object, that provides access to categories
	 * data.
	 * 
	 * @return
	 */
	CategoryDAO getCategoryDAO();

	/**
	 * Method returns data access object, that provides access to images data.
	 * 
	 * @return
	 */
	ImageDAO getImageDAO();

	/**
	 * Method returns data access object, that provides access to answers' marks
	 * data.
	 * 
	 * @return
	 */
	MarkDAO getMarkDAO();

	/**
	 * Method returns data access object, that provides access to messages data.
	 * 
	 * @return
	 */
	MessageDAO getMessageDAO();

}
