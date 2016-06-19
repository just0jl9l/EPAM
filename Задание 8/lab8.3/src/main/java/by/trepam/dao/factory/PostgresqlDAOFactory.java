package by.trepam.dao.factory;

import java.sql.Connection;

import by.trepam.dao.AccountDAO;
import by.trepam.dao.AnswerDAO;
import by.trepam.dao.CategoryDAO;
import by.trepam.dao.ImageDAO;
import by.trepam.dao.MarkDAO;
import by.trepam.dao.MessageDAO;
import by.trepam.dao.exception.DAOException;
import by.trepam.dao.impl.PostgresqlAccountDAO;
import by.trepam.dao.impl.PostgresqlAnswerDAO;
import by.trepam.dao.impl.PostgresqlCategoryDAO;
import by.trepam.dao.impl.PostgresqlImageDAO;
import by.trepam.dao.impl.PostgresqlMarkDAO;
import by.trepam.dao.impl.PostgresqlMessageDAO;
import by.trepam.dbcp.ConnectionPool;
import by.trepam.dbcp.exception.ConnectionPoolException;

public class PostgresqlDAOFactory implements DAOFactory{

	private static ConnectionPool connectionPool = new ConnectionPool();
	private AccountDAO accountDAO = new PostgresqlAccountDAO();
	private AnswerDAO answerDAO = new PostgresqlAnswerDAO();
	private CategoryDAO categoryDAO = new PostgresqlCategoryDAO();
	private ImageDAO imageDAO = new PostgresqlImageDAO();
	private MarkDAO markDAO = new PostgresqlMarkDAO();
	private MessageDAO messageDAO = new PostgresqlMessageDAO();
	
	public PostgresqlDAOFactory() throws DAOException{
		try {
			connectionPool.init();
		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPoolException",e);
		}
	}
		
	public static Connection createConnection() throws DAOException{ 
		Connection connection;
		try {
			connection = connectionPool.getConnection();
		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPoolException",e);
		}
		return connection;
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
