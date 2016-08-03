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

public class ChangeCategoryCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static ChangeCategoryCommand command = new ChangeCategoryCommand();

	private ChangeCategoryCommand() {
	}

	public static ChangeCategoryCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService service = CategoryServiceImpl.getInstance();
		try {
			Category category = (Category) request.getSession(true).getAttribute(CommandConstant.PARAM_CATEGORY);
			if (category != null) {
				Integer categoryId = category.getId();
				String titleRu = request.getParameter(CommandConstant.PARAM_TITLE_RU);
				String titleEn = request.getParameter(CommandConstant.PARAM_TITLE_EN);
				String descriptionRu = request.getParameter(CommandConstant.PARAM_DESCRIPTION_RU);
				String descriptionEn = request.getParameter(CommandConstant.PARAM_DESCRIPTION_EN);
				Object image = request.getParameter(CommandConstant.PARAM_IMAGE);
				Category categoryRu = new Category();
				categoryRu.setName(titleRu);
				categoryRu.setDescription(descriptionRu);
				categoryRu.setId(categoryId);
				Category categoryEn = new Category();
				categoryEn.setName(titleEn);
				categoryEn.setDescription(descriptionEn);
				categoryEn.setId(categoryId);
				if (titleRu != null && descriptionRu != null && !CommandConstant.EMPTY.equals(titleRu)
						&& !CommandConstant.EMPTY.equals(descriptionRu)) {
					service.updateCategory(categoryRu);
					service.updateCategoryText(categoryRu, CommandConstant.RU);
					if (titleEn != null && descriptionEn != null && !CommandConstant.EMPTY.equals(titleEn)
							&& !CommandConstant.EMPTY.equals(descriptionEn)) {
						service.updateCategoryText(categoryEn, CommandConstant.EN);
					} else {
						service.deleteCategoryText(categoryId, CommandConstant.EN);
					}
				} else {
					service.deleteCategoryText(categoryId, CommandConstant.RU);
					if (titleEn != null && descriptionEn != null && !CommandConstant.EMPTY.equals(titleEn)
							&& !CommandConstant.EMPTY.equals(descriptionEn)) {
						service.updateCategory(categoryEn);
						service.updateCategoryText(categoryEn, CommandConstant.EN);
					}
				}
				List<Category> categories = service
						.getCategories(request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
				if (categories != null) {
					request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORIES, categories);
					response.sendRedirect("../like-it/categories");
				} else {
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Categories wasn't found");
					response.sendRedirect("../like-it/error");
				}
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
				response.sendRedirect("../like-it/error");
			}
		} catch (NumberFormatException e) {
			logger.error("Wrong category id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong category id");
			response.sendRedirect("../like-it/error");

		} catch (ServiceException e) {
			logger.error("ServiceException occurred during changing category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during changing category");
			response.sendRedirect("../like-it/error");
		}
	}

}
