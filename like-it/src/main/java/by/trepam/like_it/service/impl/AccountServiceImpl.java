package by.trepam.like_it.service.impl;

import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.DAOFactory;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.AccountService;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

public class AccountServiceImpl implements AccountService {
	private static final AccountServiceImpl service = new AccountServiceImpl();
	private static final DAOFactory daoFactory = PostgresqlDAOFactory.getInstance();

	private AccountServiceImpl() {
	}

	public static AccountServiceImpl getInstance() {
		return service;
	}

	public Account logIn(String login, String password)
			throws GettingDataException, WrongDataException, DataNotFoundException {
		Account account = null;
		try {
			if (login != null && login != CommandConstant.EMPTY && password != null
					&& password != CommandConstant.EMPTY) {
				AccountDAO acdao = daoFactory.getAccountDAO();
				account = acdao.logIN(login, password);
				if (account == null) {
					throw new DataNotFoundException("Account wasn't found");
				}
			} else {
				throw new WrongDataException("Wrong data");
			}
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during logination", e);
		}
		return account;
	}

	public Account getAccount(Integer accountId)
			throws GettingDataException, DataNotFoundException, WrongDataException {
		try {
			Account account = null;
			if (accountId == null) {
				throw new WrongDataException("Wrong account ID");
			}
			AccountDAO acdao = daoFactory.getAccountDAO();
			ImageDAO imgdao = daoFactory.getImageDAO();
			account = acdao.getAccount(accountId);
			if (account != null) {
				account.setPhoto(imgdao.getImage(account.getPhoto().getId()));
				account.setRating(acdao.rating(account.getId()));
			} else {
				throw new DataNotFoundException("Account wasn't found");
			}
			return account;
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during getting account data", e);
		}
	}

	public boolean isLoginValid(String login) throws GettingDataException {
		try {
			if (login != null && !CommandConstant.EMPTY.equals(login)) {
				AccountDAO accdao = daoFactory.getAccountDAO();
				return accdao.isLoginFree(login);
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during adding message", e);
		}
	}

	public void addAccount(Account account) throws GettingDataException, WrongDataException {
		try {
			if (account == null) {
				throw new WrongDataException("Wrong account");
			}
			AccountDAO accdao = daoFactory.getAccountDAO();
			accdao.insert(account);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during adding message", e);
		}

	}

	public void updateAccount(Account account) throws GettingDataException, WrongDataException {
		try {
			if (account == null) {
				throw new WrongDataException("Wrong account");
			}
			AccountDAO accdao = daoFactory.getAccountDAO();
			accdao.update(account);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during adding message", e);
		}

	}

}
