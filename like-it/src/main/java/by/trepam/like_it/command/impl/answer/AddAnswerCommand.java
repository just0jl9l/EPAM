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
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
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
		try {
			Integer accountId = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			String text = request.getParameter(CommandConstant.PARAM_TEXT);
			Message message = (Message) request.getSession(true).getAttribute(CommandConstant.PARAM_MESSAGE);
			if (text != null && message != null && !CommandConstant.EMPTY.equals(text)) {
				int messageId = message.getId();
				Answer answer = new Answer();
				answer.setAuthor(new Account(accountId));
				answer.setText(text);
				AnswerService answerService = AnswerServiceImpl.getInstance();
				answerService.addAnswer(answer, messageId);
				try{
					MessageService messagweService = MessageServiceImpl.getInstance();
					message = messagweService.getMessage(messageId);
					request.getSession(true).setAttribute(CommandConstant.PARAM_MESSAGE, message);
					response.sendRedirect("../like-it/message");
				} catch (DataNotFoundException e) {
					logger.error("Message wasn't found", e);
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Message wasn't found");
					response.sendRedirect("../like-it/error");
				} catch (WrongDataException e) {
					logger.error("Wrong message id", e);
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong message id");
					response.sendRedirect("../like-it/error");

				} catch (GettingDataException e) {
					logger.error("GettingDataException occurred", e);
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred");
					response.sendRedirect("../like-it/error");
				}
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, CommandConstant.TRUE);
				response.sendRedirect("../like-it/add-answer");
			}
		} catch (ClassCastException e) {
			logger.error("ClassCastException occurred during adding answer", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during adding answer");
			response.sendRedirect("../like-it/error");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during adding answer", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during adding answer");
			response.sendRedirect("../like-it/error");
		} catch (WrongDataException e) {
			logger.error("Wrong data", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong data");
			response.sendRedirect("../like-it/error");
		}
	}

}