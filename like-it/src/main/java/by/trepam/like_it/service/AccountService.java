package by.trepam.like_it.service;

import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.exception.ServiceException;

public interface AccountService {

	Account logIn(String login,String password) throws ServiceException;
	Account getAccount(Integer account_id) throws ServiceException;
	boolean isLoginFree(String login) throws ServiceException;
	void addAccount(Account account) throws ServiceException;
	void updateAccount(Account account) throws ServiceException;
}
