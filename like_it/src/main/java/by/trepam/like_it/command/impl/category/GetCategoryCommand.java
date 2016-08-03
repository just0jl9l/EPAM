package by.trepam.like_it.command.impl.category;

import java.io.IOException;

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

public class GetCategoryCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static GetCategoryCommand command = new GetCategoryCommand();

	private GetCategoryCommand() {
	}

	public static GetCategoryCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService service = CategoryServiceImpl.getInstance();
		try {
			Integer categoryId = new Integer(request.getParameter(CommandConstant.PARAM_CATEGORY_ID));
			Category category = service.getCategory(categoryId, request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
			if (category != null) {
				request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORY, category);
				request.getSession(true).setAttribute(CommandConstant.PARAM_MESSAGE, category.getMessages());
				request.getRequestDispatcher("WEB-INF/jsp/category.jsp").forward(request, response);
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			logger.error("Wrong category id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
			request.getRequestDispatcher("error.jsp").forward(request, response);

		} catch (ServiceException e) {
			logger.error("ServiceException occurred during getting category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during getting category");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
