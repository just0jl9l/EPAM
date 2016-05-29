package by.trepam.news.dao;

import by.trepam.news.dao.impl.NewsDAOImpl;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();
	private INewsDAO newsDAO = new NewsDAOImpl();

	public INewsDAO getNewsDAO() {
		System.out.println("DAOFactory getNewsDAO");
		return newsDAO;
	}

	public static DAOFactory getInstance() {
		return factory;
	}
}
