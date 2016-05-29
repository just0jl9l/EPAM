package by.trepam.news.dao;

import by.trepam.news.dao.exception.DAOException;
import by.trepam.news.domain.Catalog;
import by.trepam.news.domain.News;

public interface INewsDAO {
	void saveNewNews(News news,String category,String subcategory) throws DAOException;
	Catalog getCatalog() throws DAOException;
}
