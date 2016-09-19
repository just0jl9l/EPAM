package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Category;

/**
 * Interface class of data access layer. It provides access to categories data.
 *
 */

public interface CategoryDAO {

	/**
	 * Method inserts new category data in database.
	 * 
	 * @param category
	 * @throws DAOException
	 */
	void insert(Category category) throws DAOException;

	/**
	 * Method deletes category data, if category with categoryId exists.
	 * 
	 * @param categoryId
	 * @throws DAOException
	 */
	void delete(Integer categoryId) throws DAOException;

	/**
	 * Method returns existing category (preferably in language lang) or null,
	 * if category with categoryId does not exist.
	 * 
	 * @param categoryId
	 * @param lang
	 * @return
	 * @throws DAOException
	 */
	Category getCategory(Integer categoryId, String lang) throws DAOException;

	/**
	 * Method returns list of all existing category, preferably in language
	 * lang.
	 * 
	 * @param lang
	 * @return
	 * @throws DAOException
	 */
	List<Category> getAllCategories(String lang) throws DAOException;

	/**
	 * Method updates existing category data, if this category already exists.
	 * 
	 * @param category
	 * @throws DAOException
	 */
	void update(Category category) throws DAOException;

	/**
	 * Method returns id of category with title name or null, if there is no
	 * category with title name.
	 * 
	 * @param name
	 * @return
	 * @throws DAOException
	 */
	Integer getCategoryId(String name) throws DAOException;

	/**
	 * Method inserts title and description of existing category in language
	 * lang.
	 * 
	 * @param category
	 * @param lang
	 * @throws DAOException
	 */
	void insertText(Category category, String lang) throws DAOException;

	/**
	 * Method updates title and description of existing category in language
	 * lang.
	 * 
	 * @param category
	 * @param lang
	 * @throws DAOException
	 */
	void updateText(Category category, String lang) throws DAOException;

	/**
	 * Method deletes title and description in language lang of existing
	 * category with categoryId.
	 * 
	 * @param categoryId
	 * @param lang
	 * @throws DAOException
	 */
	void deleteText(Integer categoryId, String lang) throws DAOException;

	/**
	 * Method returns existing category with categoryId, title and description
	 * in language lang or null, if there is no title and description of
	 * category with categoryId in language lang.
	 * 
	 * @param categoryId
	 * @param lang
	 * @return
	 * @throws DAOException
	 */
	Category getCategoryText(Integer categoryId, String lang) throws DAOException;
}
