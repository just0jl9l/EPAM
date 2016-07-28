package by.trepam.like_it.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class MessageCommand implements Command{
	
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		Service service = factory.getService();
		try {
			Message message = service.getMessage(new Integer(request.getParameter("message_id")));
			if(message!=null){
				request.getSession(true).setAttribute("message", message);
				request.getSession(true).setAttribute("answers", message.getAnswers());
			}
			request.getRequestDispatcher("jsp/message.jsp").forward(request, response);
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during getting message",e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}
