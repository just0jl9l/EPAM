package by.trepam.dao;

import by.trepam.dao.exception.DAOException;
import by.trepam.domain.Account;

public interface AccountDAO {

	void insert(Account account) throws DAOException ;
	void delete(int accountID) throws DAOException ;
	Account getAccount(int accountID) throws DAOException ;
//	List<Account> getAllAccounts() throws DAOException ;
	void update(Account account) throws DAOException ;
	Account logIN(String login,String password) throws DAOException ;
	double rating(int accountID) throws DAOException ;
}
