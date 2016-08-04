package by.trepam.like_it.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.trepam.like_it.dao.connection_pool.ConnectionPool;
import by.trepam.like_it.dao.connection_pool.exception.ConnectionPoolException;
import by.trepam.like_it.dao.connection_pool.impl.PostgresqlConnectionPool;

public class LikeItContextListener implements ServletContextListener {

	private ConnectionPool pool;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			if(pool!=null){
				pool.close();
			}
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("JDBC Driver error", e);
		}	
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {		
		try {
			pool = PostgresqlConnectionPool.getInstance();
			pool.init();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("JDBC Driver error", e);
		}

	
	}


}
