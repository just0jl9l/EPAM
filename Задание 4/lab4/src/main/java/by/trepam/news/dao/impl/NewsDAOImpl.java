package by.trepam.news.dao.impl;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import by.trepam.news.dao.INewsDAO;
import by.trepam.news.dao.exception.DAOException;
import by.trepam.news.dao.util.NewsMarshaller;
import by.trepam.news.dao.util.NewsUnmarshaller;
import by.trepam.news.domain.Catalog;
import by.trepam.news.domain.Category;
import by.trepam.news.domain.News;
import by.trepam.news.domain.Subcategory;

public class NewsDAOImpl implements INewsDAO{
	
	public void saveNewNews(News news,String cat,String subcat) throws DAOException{
		
		Catalog list = getCatalog();
		Category category = list.getCategoryByName(cat);
		Subcategory subcategory = category.getSubcategoryByName(subcat);
		subcategory.addNews(news);
		category.addSubcategory(subcategory);
		list.addCategory(category);
		NewsMarshaller m = new NewsMarshaller();
		try {
			try {
				m.marshal(list,"src/main/resources/new-news.xml");
			} catch (FileNotFoundException e) {
				throw new DAOException("file not found", e);
			}
		} catch (JAXBException e) {
			throw new DAOException("error occurred while writing new news", e);
		}
		
	}
		 
	public Catalog getCatalog() throws DAOException{
		
		NewsUnmarshaller unm = new NewsUnmarshaller();
		try {
			unm.unmarsh("src/main/resources/news.xml");
		} catch (JAXBException e) {
			throw new DAOException("file not found", e);
		}
		Catalog newsList = unm.getCatalog();
		return newsList;
	}
}
