package by.trepam.like_it.service.impl;

import java.util.List;

import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.CategoryDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.MarkDAO;
import by.trepam.like_it.dao.MessageDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.DAOFactory;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.domain.Mark;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;

public class LikeItService implements Service{

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

	public Message getMessage(Integer id) throws ServiceException {
		Message message = null;
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			MessageDAO mesdao = df.getMessageDAO();
			message = mesdao.getMessage(id);
			AnswerDAO ansdao = df.getAnswerDAO();
			AccountDAO acdao = df.getAccountDAO();
			ImageDAO imgdao = df.getImageDAO();
			List<Answer> answers = ansdao.getAllAnswersOfMessage(id);
			Account ac;
			for(Answer a:answers){
				ac=acdao.getAccount(a.getAuthor().getId());				
				ac.setPhoto(imgdao.getImage(ac.getPhoto().getId()));
				ac.setRating(acdao.rating(ac.getId()));
				a.setAuthor(ac);
				a.setRating(ansdao.rating(a.getId()));
			}
			message.setAnswers(answers);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during getting message",e);
		}
		return message;
	}

	public void rating(Mark mark, int answerID) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			AnswerDAO ansdao = df.getAnswerDAO();
			MarkDAO markdao = df.getMarkDAO();
			Answer answer = ansdao.getAnswer(answerID);
			if(mark.getAuthor().getId()!=answer.getAuthor().getId()){
				markdao.delete(mark.getAuthor().getId(), answerID);
				markdao.insert(mark, answerID);
			}
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during rating",e);
		}
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

	public void addAnswer(Answer answer,int messageID) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			AnswerDAO ansdao = df.getAnswerDAO();
			ansdao.insert(answer, messageID);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding answer",e);
		}
		
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

	public void addMessage(Message message, int id_category) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			MessageDAO mesdao = df.getMessageDAO();
			mesdao.insert(message,id_category);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
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

	public void deleteMessage(int id_message) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			MessageDAO mesdao = df.getMessageDAO();
			mesdao.delete(id_message);
		} catch (DAOException e) {
			throw new ServiceException("DAOException occurred during adding message",e);
		}
	}

	public void updateMessage(Message message) throws ServiceException {
		try {
			DAOFactory df = PostgresqlDAOFactory.getInstance();
			MessageDAO mesdao = df.getMessageDAO();
			mesdao.update(message);
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
