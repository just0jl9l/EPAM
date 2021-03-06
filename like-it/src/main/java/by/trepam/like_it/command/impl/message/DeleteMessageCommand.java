package by.trepam.like_it.command.impl.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.service.MessageService;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
import by.trepam.like_it.service.impl.MessageServiceImpl;

/**
 * Class of command, that is used to delete message.
 *
 */

public class DeleteMessageCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static DeleteMessageCommand command = new DeleteMessageCommand();

	private DeleteMessageCommand() {
	}

	public static DeleteMessageCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer messageId = new Integer(request.getParameter(CommandConstant.PARAM_MESSAGE_ID));
			MessageService messageService = MessageServiceImpl.getInstance();
			messageService.deleteMessage(messageId);
			response.sendRedirect("../like-it/categories");
		} catch (NumberFormatException e) {
			logger.error("Wrong id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong id");
			response.sendRedirect("../like-it/error");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during deleting message", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during deleting message");
			response.sendRedirect("../like-it/error");
		} catch (WrongDataException e) {
			logger.error("Wrong messaeg ID", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong messaeg ID");
			response.sendRedirect("../like-it/error");
		}
	}

}
