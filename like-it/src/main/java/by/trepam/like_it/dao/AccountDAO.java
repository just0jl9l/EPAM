package by.trepam.like_it.dao;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Account;

public interface AccountDAO {

	void insert(Account account) throws DAOException ;
	void delete(Integer accountID) throws DAOException ;
	Account getAccount(Integer accountID) throws DAOException ;
	void update(Account account) throws DAOException ;
	Account logIN(String login,String password) throws DAOException ;
	double rating(Integer accountID) throws DAOException ;
	boolean isLoginFree(String login) throws DAOException;
}
