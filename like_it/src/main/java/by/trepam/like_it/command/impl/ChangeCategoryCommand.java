package by.trepam.like_it.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class ChangeCategoryCommand implements Command{
	
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		Service service = factory.getService();
		try {
			Category category = (Category) request.getSession(true).getAttribute("category");
			if(category!=null){
				int categoryId=category.getId();
				Object title_ru = request.getParameter("title_ru");
				Object title_en = request.getParameter("title_en");
				Object description_ru = request.getParameter("description_ru");
				Object description_en = request.getParameter("description_en");
				Object image = request.getParameter("file");
				Category category_ru = new Category();
				category_ru.setName(title_ru.toString());
				category_ru.setDescription(description_ru.toString());
				category_ru.setId(categoryId);
				Category category_en = new Category();
				category_en.setName(title_en.toString());
				category_en.setDescription(description_en.toString());
				category_en.setId(categoryId);
				if(title_ru!=null && description_ru!=null){
					service.updateCategory(category_ru);
					service.updateCategoryText(category_ru,"ru");
					if((title_en!=null && description_en!=null)){
						service.updateCategoryText(category_en,"en");
					}else{
						service.deleteCategoryText(categoryId,"en");
					}
				}else{
					service.deleteCategoryText(categoryId,"ru");
					if(title_en!=null && description_en!=null){
						service.updateCategory(category_en);
						service.updateCategoryText(category_en,"en");
					}
				}
				List<Category> categories = service.getCategories(request.getSession(true).getAttribute("local"));
				if(categories!=null){
					request.getSession(true).setAttribute("categories", categories);
				}
				request.getRequestDispatcher("jsp/categories.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding category",e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}
