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
	private static final CategoryServiceImpl service = new CategoryServiceImpl();
	private static final DAOFactory daoFactory = PostgresqlDAOFactory.getInstance();
	
	private CategoryServiceImpl(){}
	
	public static CategoryServiceImpl getInstance(){
		return service;
	}


	public List<Category> getCategories(Object lang) throws ServiceException {
		List<Category> categories = null;
		try {
			
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			if(lang!=null){
				categories = catdao.getAllCategories(lang.toString());
			}else{
				categories = catdao.getAllCategories("en");
			}	
			ImageDAO imgdao = daoFactory.getImageDAO();
			for(Category c: categories){
				c.setImage(imgdao.getImage(c.getImage().getId()));
			}			
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during getting categories",e);
		}
		return categories;
	}

	public Category getCategory(Integer id,Object lang) throws ServiceException {
		Category category = null;
		try {
			
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			if(lang!=null){
				category = catdao.getCategory(id,lang.toString());
			}else{
				category = catdao.getCategory(id,"en");
			}
			MessageDAO messdao = daoFactory.getMessageDAO();
			AccountDAO acdao = daoFactory.getAccountDAO();
			ImageDAO imgdao = daoFactory.getImageDAO();
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
			
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.insert(category);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding category",e);
		}
		
	}

	public Integer getCategoryIdByTitle(String name) throws ServiceException {
		Integer id=-10;
		try {
			
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			id=catdao.getCategoryId(name);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding category",e);
		}
		return id;
	}

	public void addCategoryText(Category category, String lang) throws ServiceException {
		try {
			
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.insertText(category,lang);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding category text",e);
		}
	}

	public void deleteCategory(Integer id_category) throws ServiceException {
		try {
			
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.delete(id_category);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}		
	}

	public void updateCategory(Category category) throws ServiceException {
		try {
			
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.update(category);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}	
		
	}

	public void updateCategoryText(Category category, String lang) throws ServiceException {
		try {
			
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.updateText(category,lang);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
		
	}

	public void deleteCategoryText(Integer category_id, String lang) throws ServiceException {
		try {
			
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.deleteText(category_id,lang);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
		
	}
}
