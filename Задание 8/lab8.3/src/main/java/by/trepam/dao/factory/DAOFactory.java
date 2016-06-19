package by.trepam.dao.factory;

import by.trepam.dao.AccountDAO;
import by.trepam.dao.AnswerDAO;
import by.trepam.dao.CategoryDAO;
import by.trepam.dao.ImageDAO;
import by.trepam.dao.MarkDAO;
import by.trepam.dao.MessageDAO;

public interface DAOFactory {

	  AccountDAO getAccountDAO();
	  AnswerDAO getAnswerDAO();
	  CategoryDAO getCategoryDAO();
	  ImageDAO getImageDAO();
	  MarkDAO getMarkDAO();
	  MessageDAO getMessageDAO();
	  
}
