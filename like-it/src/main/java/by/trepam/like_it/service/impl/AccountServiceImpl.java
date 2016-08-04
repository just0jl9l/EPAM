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
	private static final AccountServiceImpl service = new AccountServiceImpl();
	private static final DAOFactory daoFactory = PostgresqlDAOFactory.getInstance();
	
	private AccountServiceImpl(){}
	
	public static AccountServiceImpl getInstance(){
		return service;
	}	

	public Account logIn(String login, String password) throws ServiceException {
		Account account = null;
		if(login!=null && login!="" && password!=null &&password!=""){
			try {
				
				AccountDAO acdao = daoFactory.getAccountDAO();
				account = acdao.logIN(login, password);
			} catch (DAOException e) {
				throw new ServiceException("DAOException occurred during logination",e);
			}
		}
		return account;
	}

	public Account getAccount(Integer account_id) throws ServiceException {
		Account account = null;
		try {
			
			AccountDAO acdao = daoFactory.getAccountDAO();
			ImageDAO imgdao = daoFactory.getImageDAO();
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
				
				AccountDAO accdao = daoFactory.getAccountDAO();
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
			
			AccountDAO accdao = daoFactory.getAccountDAO();
			accdao.insert(account);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
		
	}

	public void updateAccount(Account account) throws ServiceException {
		try {
			
			AccountDAO accdao = daoFactory.getAccountDAO();
			accdao.update(account);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
		
	}

}
