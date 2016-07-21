package by.trepam.like_it.dao.factory;

import java.sql.Connection;

import by.trepam.like_it.connection_pool.ConnectionPool;
import by.trepam.like_it.connection_pool.exception.ConnectionPoolException;
import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.CategoryDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.MarkDAO;
import by.trepam.like_it.dao.MessageDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.impl.PostgresqlAccountDAO;
import by.trepam.like_it.dao.impl.PostgresqlAnswerDAO;
import by.trepam.like_it.dao.impl.PostgresqlCategoryDAO;
import by.trepam.like_it.dao.impl.PostgresqlImageDAO;
import by.trepam.like_it.dao.impl.PostgresqlMarkDAO;
import by.trepam.like_it.dao.impl.PostgresqlMessageDAO;

public class PostgresqlDAOFactory implements DAOFactory{

	private static PostgresqlDAOFactory factory = new PostgresqlDAOFactory();
	private static ConnectionPool connectionPool = new ConnectionPool();
	private AccountDAO accountDAO = new PostgresqlAccountDAO();
	private AnswerDAO answerDAO = new PostgresqlAnswerDAO();
	private CategoryDAO categoryDAO = new PostgresqlCategoryDAO();
	private ImageDAO imageDAO = new PostgresqlImageDAO();
	private MarkDAO markDAO = new PostgresqlMarkDAO();
	private MessageDAO messageDAO = new PostgresqlMessageDAO();
	
	static{
		try {
			connectionPool.init();
		} catch (ConnectionPoolException e) {
			try {
				connectionPool.init();
			} catch (ConnectionPoolException e1) {
				throw new Error("ConnectionPool was't initialized");
			}			
		}
	}
	
	public static PostgresqlDAOFactory getInstance(){
		return factory;
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
