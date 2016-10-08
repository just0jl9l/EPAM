package by.trepam.like_it.dao;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Account;

/**
 * Interface class of data access layer. It provides access to accounts data.
 *
 */

public interface AccountDAO {

	/**
	 * Method inserts new account data in database.
	 * 
	 * @param account
	 * @throws DAOException
	 */
	void insert(Account account) throws DAOException;

	/**
	 * Method deletes account data, if account with accountId exists.
	 * 
	 * @param accountId
	 * @throws DAOException
	 */
	void delete(Integer accountId) throws DAOException;

	/**
	 * Method returns existing account or null, if account with accountId does
	 * not exist.
	 * 
	 * @param accountId
	 * @return
	 * @throws DAOException
	 */
	Account getAccount(Integer accountId) throws DAOException;

	/**
	 * Method updates account data, if account exists.
	 * 
	 * @param account
	 * @throws DAOException
	 */
	void update(Account account) throws DAOException;

	/**
	 * Method returns existing account by login and password or null, if account
	 * does not exist.
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws DAOException
	 */
	Account logIN(String login, String password) throws DAOException;

	/**
	 * Method returns rating of account with accountId or 0.0, if there are no
	 * marks of account answers.
	 * 
	 * @param accountId
	 * @return
	 * @throws DAOException
	 */
	Double rating(Integer accountId) throws DAOException;

	/**
	 * Method returns true, if account with this login doesn't exist in database,
	 * or false otherwise.
	 * 
	 * @param login
	 * @return
	 * @throws DAOException
	 */
	boolean isLoginFree(String login) throws DAOException;
}
