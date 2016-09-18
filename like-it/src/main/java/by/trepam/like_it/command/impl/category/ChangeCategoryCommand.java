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
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
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
		try {
			Category category = (Category) request.getSession(true).getAttribute(CommandConstant.PARAM_CATEGORY);
			if (category != null) {
				Integer categoryId = category.getId();
				String titleRu = request.getParameter(CommandConstant.PARAM_TITLE_RU);
				String titleEn = request.getParameter(CommandConstant.PARAM_TITLE_EN);
				String descriptionRu = request.getParameter(CommandConstant.PARAM_DESCRIPTION_RU);
				String descriptionEn = request.getParameter(CommandConstant.PARAM_DESCRIPTION_EN);
				Category categoryRu = null;
				Category categoryEn = null;
				if (titleRu != null && descriptionRu != null && !CommandConstant.EMPTY.equals(titleRu)
						&& !CommandConstant.EMPTY.equals(descriptionRu)) {
					categoryRu = new Category();
					categoryRu.setName(titleRu);
					categoryRu.setDescription(descriptionRu);
					categoryRu.setId(categoryId);
				}
				if (titleEn != null && descriptionEn != null && !CommandConstant.EMPTY.equals(titleEn)
						&& !CommandConstant.EMPTY.equals(descriptionEn)) {
					categoryEn = new Category();
					categoryEn.setName(titleEn);
					categoryEn.setDescription(descriptionEn);
					categoryEn.setId(categoryId);
				}
				CategoryService service = CategoryServiceImpl.getInstance();
				service.updateCategory(categoryRu, categoryEn);
				GetCategoriesCommand command = GetCategoriesCommand.getInstance();
				command.execute(request, response);
			} else {
				logger.error("Category wasn't found");
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
				response.sendRedirect("../like-it/error");
			}
		} catch (NumberFormatException e) {
			logger.error("Wrong category id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong category id");
			response.sendRedirect("../like-it/error");
		} catch (WrongDataException e) {
			logger.error("Not enough data to change category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_NOT_ALL_DATA_ERROR, CommandConstant.TRUE);
			response.sendRedirect("../like-it/add-category");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during adding category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during changing category");
			response.sendRedirect("../like-it/error");
		}
	}

}
