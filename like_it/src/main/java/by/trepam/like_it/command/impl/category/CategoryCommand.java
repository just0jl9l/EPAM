package by.trepam.like_it.command.impl.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.CategoryService;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class CategoryCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		CategoryService service = factory.getCategoryService();
		try {
			Category category = service.getCategory(new Integer(request.getParameter("category_id")),
					request.getSession(true).getAttribute("local"));
			if (category != null) {
				request.getSession(true).setAttribute("category", category);
				request.getSession(true).setAttribute("messages", category.getMessages());
			}
			request.getRequestDispatcher("jsp/category.jsp").forward(request, response);
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during getting category", e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}
