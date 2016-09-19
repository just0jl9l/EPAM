package by.trepam.like_it.service;

import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

/**
 * Interface class of service layer. It provides set of available operations on
 * accounts.
 *
 */

public interface AccountService {

	/**
	 * Method returns account with login and password, if account exists.
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws GettingDataException
	 * @throws WrongDataException
	 * @throws DataNotFoundException
	 */
	Account logIn(String login, String password) throws GettingDataException, WrongDataException, DataNotFoundException;

	/**
	 * Method returns account with accountId, if account exists.
	 * 
	 * @param accountId
	 * @return
	 * @throws GettingDataException
	 * @throws DataNotFoundException
	 * @throws WrongDataException
	 */
	Account getAccount(Integer accountId) throws GettingDataException, DataNotFoundException, WrongDataException;

	/**
	 * Method returns true, if account with login does not exist, or false
	 * otherwise.
	 * 
	 * @param login
	 * @return
	 * @throws GettingDataException
	 */
	boolean isLoginValid(String login) throws GettingDataException;

	/**
	 * Method add account, if it does not already exist.
	 * 
	 * @param account
	 * @throws GettingDataException
	 * @throws WrongDataException
	 */
	void addAccount(Account account) throws GettingDataException, WrongDataException;

	/**
	 * Method update account, if it already exists.
	 * 
	 * @param account
	 * @throws GettingDataException
	 * @throws WrongDataException
	 */
	void updateAccount(Account account) throws GettingDataException, WrongDataException;
}
