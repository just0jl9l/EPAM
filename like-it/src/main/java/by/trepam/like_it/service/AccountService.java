package by.trepam.like_it.service;

import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

public interface AccountService {

	Account logIn(String login,String password) throws GettingDataException, WrongDataException, DataNotFoundException ;
	Account getAccount(Integer accountId) throws GettingDataException, DataNotFoundException, WrongDataException;
	boolean isLoginValid(String login) throws GettingDataException;
	void addAccount(Account account) throws GettingDataException, WrongDataException;
	void updateAccount(Account account) throws GettingDataException, WrongDataException;
}
