package by.trepam.like_it.command.impl.answer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Mark;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.AnswerService;
import by.trepam.like_it.service.MessageService;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class RateCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		AnswerService answerService = factory.getAnswerService();
		MessageService messagweService = factory.getMessageService();
		try {
			Integer mark_value = new Integer(request.getParameter("mark"));
			Object account_id = request.getSession(true).getAttribute("account_id");
			Message message = (Message) request.getSession(true).getAttribute("message");
			if (account_id != null) {
				if (mark_value != null && message != null) {
					int answer_id = new Integer(request.getParameter("answer"));
					Mark mark = new Mark(mark_value, new Account((int) account_id));
					if (message != null) {
						answerService.rating(mark, answer_id);
						message = messagweService.getMessage(message.getId());
						request.getSession(true).setAttribute("message", message);
						request.getSession(true).setAttribute("answers", message.getAnswers());
					}
					request.getRequestDispatcher("jsp/message.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during rating", e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}
