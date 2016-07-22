package by.trepam.like_it.service.impl;

import java.util.List;


import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.CategoryDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.MessageDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.DAOFactory;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;

public class LikeItService implements Service{

	public Account logIn(String login, String password) throws ServiceException {
		Account account = null;
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			AccountDAO acdao = df.getAccountDAO();
			account = acdao.logIN(login, password);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during logination");
		}
		return account;
	}

	public List<Category> getCategories() throws ServiceException {
		List<Category> categories = null;
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			categories = catdao.getAllCategories();
			ImageDAO imgdao = df.getImageDAO();
			for(Category c: categories){
				c.setImage(imgdao.getImage(c.getImage().getId()));
			}
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during getting categories");
		}
		return categories;
	}

	public Category getCategory(int id) throws ServiceException {
		Category category = null;
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			category = catdao.getCategory(id);
			MessageDAO messdao = df.getMessageDAO();
			AccountDAO acdao = df.getAccountDAO();
			ImageDAO imgdao = df.getImageDAO();
			List<Message> messages = messdao.getAllMessagesOfCategory(id);
			Account ac;
			for(Message m:messages){
				ac=acdao.getAccount(m.getAuthor().getId());				
				ac.setPhoto(imgdao.getImage(ac.getPhoto().getId()));
				m.setAuthor(ac);
			}
			category.setMessages(messages);
			category.setImage(imgdao.getImage(category.getImage().getId()));
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during getting category");
		}
		return category;
	}

}
