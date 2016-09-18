package by.trepam.like_it.command.impl.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.service.CategoryService;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
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
		try {
			Integer categoryId = new Integer(request.getParameter(CommandConstant.PARAM_CATEGORY_ID));
			CategoryService service = CategoryServiceImpl.getInstance();
			service.deleteCategory(categoryId);
			GetCategoriesCommand command = GetCategoriesCommand.getInstance();
			command.execute(request, response);
		} catch (NumberFormatException | WrongDataException e) {
			logger.error("Wrong category id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Wrong category id");
			response.sendRedirect("../like-it/error");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during deleting category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during deleting category");
			response.sendRedirect("../like-it/error");
		}
	}

}
