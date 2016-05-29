package by.trepam.news.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.trepam.news.dao.DAOFactory;
import by.trepam.news.dao.INewsDAO;
import by.trepam.news.dao.exception.DAOException;
import by.trepam.news.domain.Catalog;
import by.trepam.news.domain.Category;
import by.trepam.news.domain.News;
import by.trepam.news.domain.Subcategory;
import by.trepam.news.domain.criteria.CriteriaAuthorDate;
import by.trepam.news.domain.criteria.CriteriaNameAuthor;
import by.trepam.news.domain.criteria.CriteriaNameDate;
import by.trepam.news.domain.criteria.ICriteria;
import by.trepam.news.service.IService;
import by.trepam.news.service.exception.ServiceException;

public class NewsServiceImpl implements IService{

	public void saveNewNews(String[][] n) throws ServiceException {
		System.out.println("NewsServiceImpl saveNewNews");
		News news = new News();
		if(n.length==6){
			String current = n[0][0];
			Pattern p = Pattern.compile("^[a-zA-Z_0-9-]+$");  
	        Matcher m = p.matcher(current);  
	        if(m.matches()){
	        	news.setName(current);
	        }else{
	        	throw new ServiceException("wrong input data");
	        }
			for(String s:n[1]){
				p.matcher(s);
				if(m.matches()){
					news.getProviders().addAuthor(s);
		        }else{
		        	throw new ServiceException("wrong input data");
		        }				
			}
			current = n[2][0];
			p = Pattern.compile("^[0-9]{4}[-][0-9]{2}[-][0-9]{2}$");  
			m = p.matcher(current);  
	        if(m.matches()){
	        	news.setDateOfIssue(current);
	        }else{
	        	throw new ServiceException("wrong input data");
	        }	
			news.setBody(n[3][0]);		
			String category = n[4][0];
			p = Pattern.compile("^[a-zA-Z_0-9-]+$");  
	        m = p.matcher(category);  
	        if(!m.matches()){
	        	throw new ServiceException("wrong input data");
	        }  
	        String subcategory = n[5][0];
	        m = p.matcher(subcategory);  
	        if(!m.matches()){
	        	throw new ServiceException("wrong input data");
	        }
			DAOFactory factory = DAOFactory.getInstance();
			INewsDAO newDAO = factory.getNewsDAO();
			try{
				newDAO.saveNewNews(news,category,subcategory);	
			}catch(DAOException e){
			   throw new ServiceException("error occurred while writing new news", e);
			}	
		}else{
			throw new ServiceException("wrong input data");
		}	
	}

	public News findNews(ICriteria criteria) throws ServiceException {
		System.out.println("NewsServiceImpl findNews");
		List<News> allNews = new ArrayList<News>();
		List<Category> allCategories = getCatalog().getCategories();
		int len = allCategories.size();
		for (int i = 0; i < len; i++) {
			List<Subcategory> allSubcategories = allCategories.get(i).getSubcategories();
			int len2 = allSubcategories.size();
			for (int j = 0; j < len2; j++) {
				allNews.addAll(allSubcategories.get(j).getNews());
			}
		}
		switch(criteria.getCriteriaType()){
		case AUTHOR_AND_DATE:{
			CriteriaAuthorDate cr = (CriteriaAuthorDate) criteria;
			String[] s = cr.getParams();
			for(News n : allNews){
				if(n.getProviders().hasAuthor(s[0])){
					if(s[1].equals(n.getDateOfIssue())){
						return n;
					}
				}
			}
		}break;
		case NAME_AND_DATE:{
			CriteriaNameDate cr = (CriteriaNameDate) criteria;
			String[] s = cr.getParams();
			for(News n : allNews){
				if(s[0].equals(n.getName())){
					if(s[1].equals(n.getDateOfIssue())){
						return n;
					}
				}
			}			
		}break;
		case NAME_AND_AUTHOR:{
			CriteriaNameAuthor cr = (CriteriaNameAuthor) criteria;
			String[] s = cr.getParams();
			for(News n : allNews){
				if(s[0].equals(n.getName())){
					if(n.getProviders().hasAuthor(s[1])){
						return n;
					}
				}
			}
		}break;
		}
		return null;
	}

	public Catalog getCatalog() throws ServiceException {
		System.out.println("NewsServiceImpl getCatalog");
		DAOFactory factory = DAOFactory.getInstance();
		INewsDAO newDAO = factory.getNewsDAO();
		try{
			return newDAO.getCatalog();
		}catch(DAOException e){
		   throw new ServiceException("error occurred while reading file", e);
		}
	}

}
