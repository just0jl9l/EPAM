package by.trepam.like_it.command.impl.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;
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
 * Class of command, that is used to change message.
 *
 */

public class ChangeMessageCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static ChangeMessageCommand command = new ChangeMessageCommand();

	private ChangeMessageCommand() {
	}

	public static ChangeMessageCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String title = request.getParameter(CommandConstant.PARAM_TITLE);
			String text = request.getParameter(CommandConstant.PARAM_TEXT);
			Category category = (Category) request.getSession(true).getAttribute(CommandConstant.PARAM_CATEGORY);
			Message message = (Message) request.getSession(true).getAttribute(CommandConstant.PARAM_MESSAGE);
			if (category != null && message != null) {
				MessageService messageService = MessageServiceImpl.getInstance();
				messageService.updateMessage(message, title, text);
				try {
					CategoryService service = CategoryServiceImpl.getInstance();
					category = service.getCategory(category.getId(),
							request.getSession(true).getAttribute(CommandConstant.PARAM_LOCAL));
					request.getSession(true).setAttribute(CommandConstant.PARAM_CATEGORY, category);
					response.sendRedirect("../like-it/category");
				} catch (DataNotFoundException e) {
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
					response.sendRedirect("../like-it/error");
				} catch (NumberFormatException | WrongDataException e) {
					logger.error("Wrong category id", e);
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
					response.sendRedirect("../like-it/error");
				} catch (GettingDataException e) {
					logger.error("GettingDataException occurred during getting category", e);
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
							"Exception occurred during getting category");
					response.sendRedirect("../like-it/error");
				}
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Category wasn't found");
				response.sendRedirect("../like-it/error");
			}
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during changing message", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during changing message");
			response.sendRedirect("../like-it/error");
		} catch (ClassCastException e) {
			logger.error("ClassCastException occurred during changing message", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during changing message");
			response.sendRedirect("../like-it/error");
		} catch (WrongDataException e) {
			logger.error("Wrong data", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong data");
			response.sendRedirect("../like-it/error");
		}
	}
}