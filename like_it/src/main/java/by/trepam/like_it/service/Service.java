package by.trepam.like_it.service;

import java.util.List;

import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.exception.ServiceException;

public interface Service {

	Account logIn(String login,String password) throws ServiceException;
	List<Category> getCategories() throws ServiceException;
	Category getCategory(int attribute) throws ServiceException;
}
