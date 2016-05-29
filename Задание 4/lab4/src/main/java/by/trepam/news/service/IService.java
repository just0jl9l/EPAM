package by.trepam.news.service;

import by.trepam.news.domain.Catalog;
import by.trepam.news.domain.News;
import by.trepam.news.domain.criteria.ICriteria;
import by.trepam.news.service.exception.ServiceException;

public interface IService {

	void saveNewNews(String[][] news) throws ServiceException;
	News findNews(ICriteria criteria) throws ServiceException;
	Catalog getCatalog() throws ServiceException;
}
