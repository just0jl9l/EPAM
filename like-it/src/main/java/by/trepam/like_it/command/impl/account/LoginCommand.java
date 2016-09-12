package by.trepam.like_it.command.impl.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.AccountService;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
import by.trepam.like_it.service.impl.AccountServiceImpl;

public class LoginCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static LoginCommand command = new LoginCommand();

	private LoginCommand() {
	}

	public static LoginCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountService service = AccountServiceImpl.getInstance();
		try {
			Account account = service.logIn(request.getParameter(CommandConstant.PARAM_LOGIN),
					request.getParameter(CommandConstant.PARAM_PASSWORD));
			request.getSession(true).setAttribute(CommandConstant.PARAM_ACCOUNT_ID, account.getId());
			request.getSession(true).setAttribute(CommandConstant.PARAM_STATUS, account.getStatus());
			response.sendRedirect("../like-it");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during logination", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during logination");
			response.sendRedirect("../like-it/error");
		} catch (WrongDataException e) {
			logger.error("Wrong data", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, CommandConstant.TRUE);
			response.sendRedirect("../like-it/login");
			e.printStackTrace();
		} catch (DataNotFoundException e) {
			logger.error("Account wasn't found", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, CommandConstant.TRUE);
			response.sendRedirect("../like-it/login");
		}
	}

}
