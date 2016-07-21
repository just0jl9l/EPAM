package by.trepam.like_it.dao;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Account;

public interface AccountDAO {

	void insert(Account account) throws DAOException ;
	void delete(int accountID) throws DAOException ;
	Account getAccount(int accountID) throws DAOException ;
//	List<Account> getAllAccounts() throws DAOException ;
	void update(Account account) throws DAOException ;
	Account logIN(String login,String password) throws DAOException ;
	double rating(int accountID) throws DAOException ;
}
