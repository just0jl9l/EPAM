package by.trepam.like_it.dao.factory;

import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.CategoryDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.MarkDAO;
import by.trepam.like_it.dao.MessageDAO;
import by.trepam.like_it.dao.impl.PostgresqlAccountDAO;
import by.trepam.like_it.dao.impl.PostgresqlAnswerDAO;
import by.trepam.like_it.dao.impl.PostgresqlCategoryDAO;
import by.trepam.like_it.dao.impl.PostgresqlImageDAO;
import by.trepam.like_it.dao.impl.PostgresqlMarkDAO;
import by.trepam.like_it.dao.impl.PostgresqlMessageDAO;

public class PostgresqlDAOFactory implements DAOFactory {

	private static PostgresqlDAOFactory factory = new PostgresqlDAOFactory();

	private AccountDAO accountDAO = new PostgresqlAccountDAO();
	private AnswerDAO answerDAO = new PostgresqlAnswerDAO();
	private CategoryDAO categoryDAO = new PostgresqlCategoryDAO();
	private ImageDAO imageDAO = new PostgresqlImageDAO();
	private MarkDAO markDAO = new PostgresqlMarkDAO();
	private MessageDAO messageDAO = new PostgresqlMessageDAO();

	private PostgresqlDAOFactory() {
	}

	public static PostgresqlDAOFactory getInstance() {
		return factory;
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public AnswerDAO getAnswerDAO() {
		return answerDAO;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	public MarkDAO getMarkDAO() {
		return markDAO;
	}

	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

}
