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
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
import by.trepam.like_it.service.impl.CategoryServiceImpl;

/**
 * Class of command, that is used to delete existing category.
 *
 */

public class DeleteCategoryCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static DeleteCategoryCommand command = new DeleteCategoryCommand();

	private DeleteCategoryCommand() {
	}

	public static DeleteCategoryCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer categoryId = new Integer(request.getParameter(CommandConstant.PARAM_CATEGORY_ID));
			CategoryService service = CategoryServiceImpl.getInstance();
			service.deleteCategory(categoryId);
			try {
				CategoryService service1 = CategoryServiceImpl.getInstance();
				List<Category> categories = service1
						.getCategories(request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
				request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORIES, categories);
				response.sendRedirect("../like-it/categories");

			} catch (GettingDataException e) {
				logger.error("GettingDataException occurred during getting categories", e);
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
						"Exception occurred during getting categories");
				response.sendRedirect("../like-it/error");
			}
		} catch (NumberFormatException | WrongDataException e) {
			logger.error("Wrong category id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong category id");
			response.sendRedirect("../like-it/error");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during deleting category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during deleting category");
			response.sendRedirect("../like-it/error");
		}
	}

}
