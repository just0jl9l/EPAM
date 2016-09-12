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
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
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
			Integer markValue = new Integer(request.getParameter(CommandConstant.PARAM_MARK));
			Integer accountId = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			int answerId = new Integer(request.getParameter(CommandConstant.PARAM_ANSWER));
			Message message = (Message) request.getSession(true).getAttribute(CommandConstant.PARAM_MESSAGE);
			Mark mark = new Mark(markValue, new Account(accountId));
			if (message != null) {
				answerService.rating(mark, answerId);
				message = messagweService.getMessage(message.getId());
				if (message != null) {								//?
					request.getSession(true).setAttribute(CommandConstant.PARAM_MESSAGE, message);
					response.sendRedirect("../like-it/message");
				}else{
					logger.error("Message wasn't found");
					request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Message wasn't found");
					response.sendRedirect("../like-it/error");
				}
			}else{
				response.sendRedirect("../like-it/like-it");
			}
		} catch (NumberFormatException e) {
			logger.error("Wrong account id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account id");
			response.sendRedirect("../like-it/error");
		} catch (ClassCastException e) {
			logger.error("ClassCastException occurred during rating", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during rating");
			response.sendRedirect("../like-it/error");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during rating", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during rating");
			response.sendRedirect("../like-it/error");
		} catch (WrongDataException e) {
			logger.error("Wrong rating data", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong rating data");
			response.sendRedirect("../like-it/error");
		} catch (DataNotFoundException e) {
			logger.error("Answer wasn't found", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Answer wasn't found");
			response.sendRedirect("../like-it/error");
		}
	}

}
