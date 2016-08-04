package by.trepam.like_it.dao.factory;

import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.CategoryDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.MarkDAO;
import by.trepam.like_it.dao.MessageDAO;

public interface DAOFactory {

	  AccountDAO getAccountDAO();
	  AnswerDAO getAnswerDAO();
	  CategoryDAO getCategoryDAO();
	  ImageDAO getImageDAO();
	  MarkDAO getMarkDAO();
	  MessageDAO getMessageDAO();
	  
}
