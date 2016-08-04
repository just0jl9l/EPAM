package by.trepam.like_it.command.impl.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.CategoryService;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.impl.CategoryServiceImpl;

public class GetCategoriesCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static GetCategoriesCommand command = new GetCategoriesCommand();

	private GetCategoriesCommand() {
	}

	public static GetCategoriesCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService service = CategoryServiceImpl.getInstance();
		try {
			List<Category> categories = service.getCategories(request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
			if (categories != null && !categories.isEmpty()) {
				request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORIES, categories);
				request.getRequestDispatcher("WEB-INF/jsp/categories.jsp").forward(request, response);
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Categories wasn't found");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during getting categories", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during getting categories");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}