package by.trepam.like_it.command.impl;

import java.io.IOException;

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

public class GotoChangePersonalAccountCommand implements Command{
	
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		Service service = factory.getService();
		try {
			Category category = (Category) request.getSession(true).getAttribute("category");
			if(category!=null){
				Category category1 = service.getCategory(category.getId(),"en");
				if(category1!=null){
					request.getSession(true).setAttribute("category_en", category1);
				}
				Category category2 = service.getCategory(category.getId(),"ru");
				if(category2!=null){
					request.getSession(true).setAttribute("category_ru", category2);
				}
				request.setAttribute("change", "yes");
				request.getRequestDispatcher("jsp/add_category.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during getting category",e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}