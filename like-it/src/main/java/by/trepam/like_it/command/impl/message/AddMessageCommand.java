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
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
import by.trepam.like_it.service.impl.CategoryServiceImpl;
import by.trepam.like_it.service.impl.MessageServiceImpl;

/**
 * Class of command, that is used to add message of logged user.
 *
 */

public class AddMessageCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static AddMessageCommand command = new AddMessageCommand();

	private AddMessageCommand() {
	}

	public static AddMessageCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String title = request.getParameter(CommandConstant.PARAM_TITLE);
			String text = request.getParameter(CommandConstant.PARAM_TEXT);
			Category category = (Category) request.getSession(true).getAttribute(CommandConstant.PARAM_CATEGORY);
			Integer accountId = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			if (category != null) {
				if (title != null && text != null && !CommandConstant.EMPTY.equals(title)
						&& !CommandConstant.EMPTY.equals(text)) {
					Message message = new Message();
					message.setName(title);
					message.setText(text);
					message.setAuthor(new Account(accountId));
					MessageService messageService = MessageServiceImpl.getInstance();
					messageService.addMessage(message, category.getId());
					try {
						CategoryService service = CategoryServiceImpl.getInstance();
						category = service.getCategory(category.getId(),
								request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
						request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORY, category);
						request.getRequestDispatcher("WEB-INF/jsp/category.jsp").forward(request, response);
					} catch (DataNotFoundException e) {
						request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					} catch (NumberFormatException | WrongDataException e) {
						logger.error("Wrong category id", e);
						request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					} catch (GettingDataException e) {
						logger.error("GettingDataException occurred during getting category", e);
						request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
								"Exception occurred during getting category");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				} else {
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, CommandConstant.TRUE);
					response.sendRedirect("../like-it/add-message");
				}
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
				response.sendRedirect("../like-it/error");
			}
		} catch (ClassCastException e) {
			logger.error("Wrong category", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong category");
			response.sendRedirect("../like-it/error");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during adding message", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during adding message");
			response.sendRedirect("../like-it/error");
		} catch (WrongDataException e) {
			logger.error("Wrong message", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong message");
			response.sendRedirect("../like-it/error");
		}
	}
}
