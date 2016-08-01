package by.trepam.like_it.service.impl;

import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.DAOFactory;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.AccountService;
import by.trepam.like_it.service.exception.ServiceException;

public class AccountServiceImpl implements AccountService{

	public Account logIn(String login, String password) throws ServiceException {
		Account account = null;
		if(login!=null && login!="" && password!=null &&password!=""){
			try {
				DAOFactory df = PostgresqlDAOFactory.getInstance();
				AccountDAO acdao = df.getAccountDAO();
				account = acdao.logIN(login, password);
			} catch (DAOException e) {
				throw new ServiceException("DAOException occurred during logination",e);
			}
		}
		return account;
	}

	public Account getAccount(int account_id) throws ServiceException {
		Account account = null;
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			AccountDAO acdao = df.getAccountDAO();
			ImageDAO imgdao = df.getImageDAO();
			account = acdao.getAccount(account_id);
			if(account!=null){				
				account.setPhoto(imgdao.getImage(account.getPhoto().getId()));
				account.setRating(acdao.rating(account.getId()));
			}
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during getting account data",e);
		}
		return account;
	}

	public boolean isLoginFree(String login) throws ServiceException {
		try {
			if(login!=null && !"".equals(login)){
				DAOFactory df = PostgresqlDAOFactory.getInstance();
				AccountDAO accdao = df.getAccountDAO();
				return accdao.isLoginFree(login);
			}else{
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
	}

	public void addAccount(Account account) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			AccountDAO accdao = df.getAccountDAO();
			accdao.insert(account);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
		
	}

	public void updateAccount(Account account) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			AccountDAO accdao = df.getAccountDAO();
			accdao.update(account);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
		
	}

}
