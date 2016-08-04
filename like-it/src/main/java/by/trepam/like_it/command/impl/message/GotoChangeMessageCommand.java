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

public class GotoChangeMessageCommand implements Command {
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static GotoChangeMessageCommand command = new GotoChangeMessageCommand();

	private GotoChangeMessageCommand() {
	}

	public static GotoChangeMessageCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Message message = (Message) request.getSession(true).getAttribute(CommandConstant.PARAM_MESSAGE);
			if (message != null) {
				request.setAttribute(CommandConstant.PARAM_CHANGE, CommandConstant.TRUE);
				request.getRequestDispatcher("WEB-INF/jsp/add-message.jsp").forward(request, response);
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Message wasn't found");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (ClassCastException e) {
			logger.error("ClassCastException occurred", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}