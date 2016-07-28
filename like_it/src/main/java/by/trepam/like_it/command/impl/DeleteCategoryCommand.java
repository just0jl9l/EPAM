package by.trepam.like_it.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class DeleteCategoryCommand implements Command{
	
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		Service service = factory.getService();
		try {
			Object category_id = request.getParameter("category_id");
			if(category_id!=null){
				service.deleteCategory(new Integer(category_id.toString()));
				List<Category> categories = service.getCategories(request.getSession(true).getAttribute("local"));
				if(categories!=null){
					request.getSession(true).setAttribute("categories", categories);
				}
			}
			request.getRequestDispatcher("jsp/categories.jsp").forward(request, response);
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding category",e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}
