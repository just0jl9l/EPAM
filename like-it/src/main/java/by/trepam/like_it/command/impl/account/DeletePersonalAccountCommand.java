package by.trepam.like_it.command.impl.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.service.AccountService;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
import by.trepam.like_it.service.impl.AccountServiceImpl;

public class DeletePersonalAccountCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static DeletePersonalAccountCommand command = new DeletePersonalAccountCommand();

	private DeletePersonalAccountCommand() {
	}

	public static DeletePersonalAccountCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer accountId = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			AccountService service1 = AccountServiceImpl.getInstance();
			service1.deleteAccount(accountId);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ACCOUNT_ID, null);
			request.getSession(true).setAttribute(CommandConstant.PARAM_STATUS, null);
			response.sendRedirect("../like-it");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during getting personal data", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during getting personal data");
			response.sendRedirect("../like-it/error");
		} catch (WrongDataException e) {
			logger.error("Wrong account ID", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account ID");
			response.sendRedirect("../like-it/error");
		}

	}

}
