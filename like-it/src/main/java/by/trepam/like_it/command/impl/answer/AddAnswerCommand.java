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
import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.AnswerService;
import by.trepam.like_it.service.MessageService;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.impl.AnswerServiceImpl;
import by.trepam.like_it.service.impl.MessageServiceImpl;

public class AddAnswerCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static AddAnswerCommand command = new AddAnswerCommand();

	private AddAnswerCommand() {
	}

	public static AddAnswerCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnswerService answerService = AnswerServiceImpl.getInstance();
		MessageService messagweService = MessageServiceImpl.getInstance();
		try {
			Integer account_id = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			String text = request.getParameter(CommandConstant.PARAM_TEXT);
			Message message = (Message) request.getSession(true).getAttribute(CommandConstant.PARAM_MESSAGE);
			if (text != null && message != null && !CommandConstant.EMPTY.equals(text)) {
				Answer answer = new Answer();
				answer.setAuthor(new Account(account_id));
				answer.setText(text);
				answerService.addAnswer(answer, message.getId());
				message = messagweService.getMessage(message.getId());
				if (message != null) {
					request.getSession(true).setAttribute(CommandConstant.PARAM_MESSAGE, message);
					request.getSession(true).setAttribute(CommandConstant.PARAM_ANSWER, message.getAnswers());
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
			logger.error("ClassCastException occurred during adding answer", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during adding answer");
			response.sendRedirect("../like-it/error");
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding answer", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during adding answer");
			response.sendRedirect("../like-it/error");
		}
	}

}