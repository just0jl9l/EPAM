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

public class AddCategoryCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static AddCategoryCommand command = new AddCategoryCommand();

	private AddCategoryCommand() {
	}

	public static AddCategoryCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService service = CategoryServiceImpl.getInstance();
		try {
			String titleRu = request.getParameter(CommandConstant.PARAM_TITLE_RU);
			String titleEn = request.getParameter(CommandConstant.PARAM_TITLE_EN);
			String descriptionRu = request.getParameter(CommandConstant.PARAM_DESCRIPTION_RU);
			String descriptionEn = request.getParameter(CommandConstant.PARAM_DESCRIPTION_EN);
			Object image = request.getParameter(CommandConstant.PARAM_IMAGE);
			Category categoryRu = new Category();
			categoryRu.setName(titleRu);
			categoryRu.setDescription(descriptionRu);
			Category categoryEn = new Category();
			categoryEn.setName(titleEn);
			categoryEn.setDescription(descriptionEn);
			if (titleRu != null && descriptionRu != null && !CommandConstant.EMPTY.equals(titleRu)
					&& !CommandConstant.EMPTY.equals(descriptionRu)) {
				service.addCategory(categoryRu);
				Integer id = service.getCategoryIdByTitle(titleRu);
				categoryRu.setId(id);
				service.addCategoryText(categoryRu, CommandConstant.RU);
				if (titleEn != null && descriptionEn != null && !CommandConstant.EMPTY.equals(titleEn)
						&& !CommandConstant.EMPTY.equals(descriptionEn)) {
					categoryEn.setId(id);
					service.addCategoryText(categoryEn, CommandConstant.EN);
				}
			} else {
				if (titleEn != null && descriptionEn != null && !CommandConstant.EMPTY.equals(titleEn)
						&& !CommandConstant.EMPTY.equals(descriptionEn)) {
					service.addCategory(categoryEn);
					Integer id = service.getCategoryIdByTitle(titleEn);
					categoryEn.setId(id);
					service.addCategoryText(categoryEn, CommandConstant.EN);
				}
			}
			List<Category> categories = service
					.getCategories(request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
			if (categories != null) {
				request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORIES, categories);
			}
			response.sendRedirect("../like-it/categories");
		} catch (NumberFormatException e) {
			logger.error("Wrong account id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account id");
			response.sendRedirect("../like-it/error");

		} catch (ClassCastException e) {
			logger.error("ClassCastException occurred during adding category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during adding category");
			response.sendRedirect("../like-it/error");
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during adding category");
			response.sendRedirect("../like-it/error");
		}
	}

}
