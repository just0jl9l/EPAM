package by.trepam.like_it.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class CategoriesCommand implements Command{
	
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public HttpServletRequest execute(HttpServletRequest request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		Service service = factory.getService();
		try {
			List<Category> categories = service.getCategories();
			if(categories!=null){
				request.getSession(true).setAttribute("categories", categories);
			}
			request.setAttribute("next_page", "jsp/categories.jsp");
		} catch (ServiceException e) {
			request.setAttribute("next_page", "jsp/like_it.jsp");
			logger.error("ServiceException occurred during getting categories");
		}
		return request;
	}

}
