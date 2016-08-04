package by.trepam.like_it.dao.connection_pool;

import java.sql.Connection;

import by.trepam.like_it.dao.connection_pool.exception.ConnectionPoolException;

public interface ConnectionPool {

	void init() throws ConnectionPoolException;
	Connection getConnection() throws ConnectionPoolException;
	void close() throws ConnectionPoolException;

}
