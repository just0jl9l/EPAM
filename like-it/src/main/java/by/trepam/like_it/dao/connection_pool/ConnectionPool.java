package by.trepam.like_it.dao.connection_pool;

import java.sql.Connection;

import by.trepam.like_it.dao.connection_pool.exception.ConnectionPoolException;

/** 
 * Interface class. It provides connections to database management system.
 *
 */

public interface ConnectionPool {

	/**
	 * Method initializes connection pool.
	 * 
	 * @throws ConnectionPoolException
	 */
	void init() throws ConnectionPoolException;

	/**
	 * Method returns free open connection.
	 * 
	 * @return
	 * @throws ConnectionPoolException
	 */
	Connection getConnection() throws ConnectionPoolException;

	/**
	 * Method closes all connections of connection pool.
	 * 
	 * @throws ConnectionPoolException
	 */
	void close() throws ConnectionPoolException;

}
