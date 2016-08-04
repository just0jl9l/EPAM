package by.trepam.like_it.command.impl.answer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Mark;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.AnswerService;
import by.trepam.like_it.service.MessageService;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.impl.AnswerServiceImpl;
import by.trepam.like_it.service.impl.MessageServiceImpl;

public class RateCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static RateCommand command = new RateCommand();

	private RateCommand() {
	}

	public static RateCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnswerService answerService = AnswerServiceImpl.getInstance();
		MessageService messagweService = MessageServiceImpl.getInstance();
		try {
			Integer mark_value = new Integer(request.getParameter(CommandConstant.PARAM_MARK));
			Integer accountId = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			Message message = (Message) request.getSession(true).getAttribute(CommandConstant.PARAM_MESSAGE);
			if (mark_value != null && message != null) {
				int answer_id = new Integer(request.getParameter(CommandConstant.PARAM_ANSWER));
				Mark mark = new Mark(mark_value, new Account(accountId));
				if (message != null) {
					answerService.rating(mark, answer_id);
					message = messagweService.getMessage(message.getId());
					request.getSession(true).setAttribute(CommandConstant.PARAM_MESSAGE, message);
				}
				response.sendRedirect("../like-it/message");
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong data");
				response.sendRedirect("../like-it/error");
			}			
		} catch (NumberFormatException e) {
			logger.error("Wrong account id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account id");
			response.sendRedirect("../like-it/error");
		} catch (ClassCastException e) {
			logger.error("ClassCastException occurred during rating", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during rating");
			response.sendRedirect("../like-it/error");
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during rating", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during rating");
			response.sendRedirect("../like-it/error");
		}
	}

}
