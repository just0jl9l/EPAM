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
import by.trepam.like_it.service.CategoryService;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.impl.CategoryServiceImpl;

public class SitemapCommand implements Command {
	private final static SitemapCommand command = new SitemapCommand();
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	
	private SitemapCommand(){}
	
	public static SitemapCommand getInstance(){
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService service = CategoryServiceImpl.getInstance();
		try {
			List<Category> categories = service.getCategories(request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
			request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORIES, categories);
			request.getRequestDispatcher("WEB-INF/jsp/sitemap.jsp").forward(request, response);
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during getting sitemap", e);
			request.getRequestDispatcher("like-it.jsp").forward(request, response);
		}
	}

}
