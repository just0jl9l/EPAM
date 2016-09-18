package by.trepam.like_it.dao;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Account;

public interface AccountDAO {

	void insert(Account account) throws DAOException ;
	void delete(Integer accountId) throws DAOException ;
	Account getAccount(Integer accountId) throws DAOException ;
	void update(Account account) throws DAOException ;
	Account logIN(String login,String password) throws DAOException ;
	Double rating(Integer accountId) throws DAOException ;
	boolean isLoginFree(String login) throws DAOException;
}
