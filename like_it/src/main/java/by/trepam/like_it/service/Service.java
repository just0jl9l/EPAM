package by.trepam.like_it.service;

import java.util.List;

import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.domain.Mark;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.exception.ServiceException;

public interface Service {

	Account logIn(String login,String password) throws ServiceException;
	List<Category> getCategories(Object lang) throws ServiceException;
	Category getCategory(int category_id, Object lang) throws ServiceException;
	Message getMessage(Integer message_id) throws ServiceException;
	void rating(Mark mark, int answer_id) throws ServiceException;
	Account getAccount(int account_id) throws ServiceException;
	void addAnswer(Answer answer,int messageID) throws ServiceException;
	void addCategory(Category category) throws ServiceException;
	int getCategoryId(String name) throws ServiceException;
	void addCategoryText(Category category, String lang) throws ServiceException;
	void addMessage(Message message, int id_category) throws ServiceException;
	void updateMessage(Message message) throws ServiceException;
	void deleteCategory(int id_category) throws ServiceException;
	void deleteMessage(int id_message) throws ServiceException;
	void updateCategory(Category category) throws ServiceException;
	void updateCategoryText(Category category, String lang) throws ServiceException;
	void deleteCategoryText(int category_id, String lang) throws ServiceException;
	boolean isLoginFree(String login) throws ServiceException;
	void addAccount(Account account) throws ServiceException;
	void updateAccount(Account account) throws ServiceException;
}
