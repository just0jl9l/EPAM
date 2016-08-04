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
import by.trepam.like_it.service.exception.ServiceException;
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
		MessageService service = MessageServiceImpl.getInstance();
		try {
			Integer messageId = new Integer(request.getParameter(CommandConstant.PARAM_MESSAGE_ID));
			Message message = service.getMessage(messageId);
			if (message != null) {
				request.getSession(true).setAttribute(CommandConstant.PARAM_MESSAGE, message);
				request.getSession(true).setAttribute(CommandConstant.PARAM_ANSWER, message.getAnswers());
				request.getRequestDispatcher("WEB-INF/jsp/message.jsp").forward(request, response);
			}else{
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Message wasn't found");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch(NumberFormatException e){
			logger.error("Wrong message id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong message id");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			
		} catch (ServiceException e) {
			logger.error("ServiceException occurred", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
