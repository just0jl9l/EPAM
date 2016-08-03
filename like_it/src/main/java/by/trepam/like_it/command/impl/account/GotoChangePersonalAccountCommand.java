package by.trepam.like_it.command.impl.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;

public class GotoChangePersonalAccountCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static GotoChangePersonalAccountCommand command = new GotoChangePersonalAccountCommand();

	private GotoChangePersonalAccountCommand() {
	}

	public static GotoChangePersonalAccountCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Integer accountId = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			if (accountId != null) {
				request.setAttribute(CommandConstant.PARAM_CHANGE, CommandConstant.TRUE);
				request.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			logger.error("Wrong account id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account id");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}