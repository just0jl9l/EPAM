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
import by.trepam.like_it.service.CategoryService;
import by.trepam.like_it.service.exception.ServiceException;

public class CategoryServiceImpl implements CategoryService{


	public List<Category> getCategories(Object lang) throws ServiceException {
		List<Category> categories = null;
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			if(lang!=null){
				categories = catdao.getAllCategories(lang.toString());
			}else{
				categories = catdao.getAllCategories("en");
			}	
			ImageDAO imgdao = df.getImageDAO();
			for(Category c: categories){
				c.setImage(imgdao.getImage(c.getImage().getId()));
			}			
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during getting categories",e);
		}
		return categories;
	}

	public Category getCategory(int id,Object lang) throws ServiceException {
		Category category = null;
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			if(lang!=null){
				category = catdao.getCategory(id,lang.toString());
			}else{
				category = catdao.getCategory(id,"en");
			}
			MessageDAO messdao = df.getMessageDAO();
			AccountDAO acdao = df.getAccountDAO();
			ImageDAO imgdao = df.getImageDAO();
			List<Message> messages = messdao.getAllMessagesOfCategory(id);
			Account ac;
			for(Message m:messages){
				ac=acdao.getAccount(m.getAuthor().getId());				
				ac.setPhoto(imgdao.getImage(ac.getPhoto().getId()));
				ac.setRating(acdao.rating(ac.getId()));
				m.setAuthor(ac);
			}
			category.setMessages(messages);
			category.setImage(imgdao.getImage(category.getImage().getId()));
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during getting category",e);
		}
		return category;
	}

	public void addCategory(Category category) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			catdao.insert(category);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding category",e);
		}
		
	}

	public int getCategoryId(String name) throws ServiceException {
		int id=-10;
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			id=catdao.getCategoryId(name);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding category",e);
		}
		return id;
	}

	public void addCategoryText(Category category, String lang) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			catdao.insertText(category,lang);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding category text",e);
		}
	}

	public void deleteCategory(int id_category) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			catdao.delete(id_category);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}		
	}

	public void updateCategory(Category category) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			catdao.update(category);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}	
		
	}

	public void updateCategoryText(Category category, String lang) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			catdao.updateText(category,lang);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
		
	}

	public void deleteCategoryText(int category_id, String lang) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			CategoryDAO catdao = df.getCategoryDAO();
			catdao.deleteText(category_id,lang);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
		
	}
}
