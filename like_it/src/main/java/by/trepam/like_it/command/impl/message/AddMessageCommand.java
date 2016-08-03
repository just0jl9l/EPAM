package by.trepam.like_it.command.impl.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.CategoryService;
import by.trepam.like_it.service.MessageService;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.impl.CategoryServiceImpl;
import by.trepam.like_it.service.impl.MessageServiceImpl;

public class AddMessageCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static AddMessageCommand command = new AddMessageCommand();

	private AddMessageCommand() {
	}

	public static AddMessageCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageService messageService = MessageServiceImpl.getInstance();
		CategoryService categoryService = CategoryServiceImpl.getInstance();
		try {
			String title = request.getParameter(CommandConstant.PARAM_TITLE);
			String text = request.getParameter(CommandConstant.PARAM_TEXT);
			Category category = (Category) request.getSession(true).getAttribute(CommandConstant.PARAM_CATEGORY);
			Integer accountId = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			if (accountId != null) {
				if (category != null) {
					if (title != null && text != null && !CommandConstant.EMPTY.equals(title)
							&& !CommandConstant.EMPTY.equals(text)) {
						Message message = new Message();
						message.setName(title);
						message.setText(text);
						message.setAuthor(new Account(accountId));
						messageService.addMessage(message, category.getId());
						category = categoryService.getCategory(category.getId(),
								request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
						if (category != null) {
							request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORY, category);
							request.getSession(true).setAttribute(CommandConstant.PARAM_MESSAGE, category.getMessages());
							response.sendRedirect("../like-it/category");
						}else{
							request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
							response.sendRedirect("../like-it/error");
						}
					}else{
						request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong data");
						response.sendRedirect("../like-it/error");
					}					
				} else {
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
					response.sendRedirect("../like-it/error");
				}
			} else {
				response.sendRedirect("../like-it/login");
			}
		} catch (NumberFormatException e) {
			logger.error("Wrong account id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account id");
			response.sendRedirect("../like-it/error");

		} catch (ClassCastException e) {
			logger.error("ClassCastException occurred during adding message", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during adding message");
			response.sendRedirect("../like-it/error");
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding message", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during adding message");
			response.sendRedirect("../like-it/error");
		}
	}
}