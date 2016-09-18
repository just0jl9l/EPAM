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

public class AddCategoryCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static AddCategoryCommand command = new AddCategoryCommand();

	private AddCategoryCommand() {
	}

	public static AddCategoryCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
			}
			if (titleEn != null && descriptionEn != null && !CommandConstant.EMPTY.equals(titleEn)
					&& !CommandConstant.EMPTY.equals(descriptionEn)) {
				categoryEn = new Category();
				categoryEn.setName(titleEn);
				categoryEn.setDescription(descriptionEn);
			}
			CategoryService service = CategoryServiceImpl.getInstance();
			service.addCategory(categoryRu, categoryEn);
			GetCategoriesCommand command = GetCategoriesCommand.getInstance();
			command.execute(request, response);
		}catch (WrongDataException e){
			logger.error("WrongDataException occurred during adding category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_NOT_ALL_DATA_ERROR, CommandConstant.TRUE);
			response.sendRedirect("../like-it/add-category");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during adding category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during adding category");
			response.sendRedirect("../like-it/error");
		}
	}

}
