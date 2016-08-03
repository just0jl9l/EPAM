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

public class GotoChangeCategoryCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static GotoChangeCategoryCommand command = new GotoChangeCategoryCommand();

	private GotoChangeCategoryCommand() {
	}

	public static GotoChangeCategoryCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService service = CategoryServiceImpl.getInstance();
		try {
			Category category = (Category) request.getSession(true).getAttribute(CommandConstant.PARAM_CATEGORY);
			if (category != null) {
				Category currentCategory = service.getCategory(category.getId(), CommandConstant.EN);
				if (currentCategory != null) {
					request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORY_EN, currentCategory);
				}
				currentCategory = service.getCategory(category.getId(), CommandConstant.RU);
				if (currentCategory != null) {
					request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORY_RU, currentCategory);
				}
				request.setAttribute(CommandConstant.PARAM_CHANGE, CommandConstant.TRUE);
				request.getRequestDispatcher("WEB-INF/jsp/add-category.jsp").forward(request, response);
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (ClassCastException e) {
			logger.error("ClassCastException occurred", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (ServiceException e) {
			logger.error("ServiceException occurred", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
