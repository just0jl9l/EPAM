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

/**
 * Class of command, that is used to get account data of logged user.
 *
 */

public class GetPersonalAccountCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static GetPersonalAccountCommand command = new GetPersonalAccountCommand();

	private GetPersonalAccountCommand() {
	}

	public static GetPersonalAccountCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer accountID = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			AccountService service = AccountServiceImpl.getInstance();
			Account account = service.getAccount(accountID);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ACCOUNT, account);
			request.getRequestDispatcher("WEB-INF/jsp/personal-account.jsp").forward(request, response);
		} catch (ClassCastException e) {
			logger.error("Wrong account id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account id");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during getting personal data", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during getting personal data");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (DataNotFoundException e) {
			logger.error("Account wasn't found", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Account wasn't found");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (WrongDataException e) {
			logger.error("Wrong account ID", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account ID");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
