package by.trepam.like_it.command.impl.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.MessageService;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
import by.trepam.like_it.service.impl.MessageServiceImpl;

public class GetMessageCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static GetMessageCommand command = new GetMessageCommand();

	private GetMessageCommand() {
	}

	public static GetMessageCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer messageId = new Integer(request.getParameter(CommandConstant.PARAM_MESSAGE_ID));
			MessageService service = MessageServiceImpl.getInstance();
			Message message = service.getMessage(messageId);
			request.getSession(true).setAttribute(CommandConstant.PARAM_MESSAGE, message);
			request.getRequestDispatcher("WEB-INF/jsp/message.jsp").forward(request, response);
		} catch (DataNotFoundException e) {
			logger.error("Message wasn't found", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Message wasn't found");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (NumberFormatException | WrongDataException e) {
			logger.error("Wrong message id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong message id");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
