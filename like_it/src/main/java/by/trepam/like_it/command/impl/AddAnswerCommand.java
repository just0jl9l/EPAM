package by.trepam.like_it.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class AddAnswerCommand implements Command{
	
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		Service service = factory.getService();
		try {
			Object account_id = request.getSession(true).getAttribute("account_id");
			Object text = request.getParameter("text");
			Object message = request.getSession(true).getAttribute("message");
			if(account_id!=null){
				if(text!=null && message!=null){
					Answer answer = new Answer();
					answer.setAuthor(new Account((int) account_id));
					answer.setText(text.toString());
					service.addAnswer(answer,((Message)message).getId());
					message = service.getMessage(((Message) message).getId());
					if(message!=null){
						request.getSession(true).setAttribute("message", message);
						request.getSession(true).setAttribute("answers", ((Message) message).getAnswers());
					}
					request.getRequestDispatcher("jsp/message.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
				}
			}else{
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding answer",e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}