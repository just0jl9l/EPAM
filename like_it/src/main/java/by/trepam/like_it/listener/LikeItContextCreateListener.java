package by.trepam.like_it.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;



public class LikeItContextCreateListener implements ServletContextListener {

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		PostgresqlDAOFactory.destroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {		
		PostgresqlDAOFactory.init();
	}

}
