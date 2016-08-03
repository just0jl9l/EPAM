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

public class DeleteCategoryCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static DeleteCategoryCommand command = new DeleteCategoryCommand();

	private DeleteCategoryCommand() {
	}

	public static DeleteCategoryCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService service = CategoryServiceImpl.getInstance();
		try {
			Integer categoryId = new Integer(request.getParameter(CommandConstant.PARAM_CATEGORY_ID));
			Integer accountId = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			if (accountId != null) {
				service.deleteCategory(categoryId);
				List<Category> categories = service.getCategories(request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
				if (categories != null && !categories.isEmpty()) {
					response.sendRedirect("../like-it/categories");
				} else {
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Categories wasn't found");
					response.sendRedirect("../like-it/error");
				}
			} else {
				response.sendRedirect("../like-it/login");
			}
		} catch (NumberFormatException e) {
			logger.error("Wrong id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during adding category");
			response.sendRedirect("../like-it/error");

		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during adding category");
			response.sendRedirect("../like-it/error");
		}
	}

}
