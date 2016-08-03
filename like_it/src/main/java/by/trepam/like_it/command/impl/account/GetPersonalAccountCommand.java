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
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.impl.AccountServiceImpl;

public class GetPersonalAccountCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static GetPersonalAccountCommand command = new GetPersonalAccountCommand();

	private GetPersonalAccountCommand() {
	}

	public static GetPersonalAccountCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountService service = AccountServiceImpl.getInstance();
		try {
			Integer accountID = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			if (accountID != null) {
				Account account = service.getAccount(accountID);
				request.getSession(true).setAttribute(CommandConstant.PARAM_ACCOUNT, account);
				request.getRequestDispatcher("WEB-INF/jsp/personal-account.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			logger.error("Wrong account id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account id");
			request.getRequestDispatcher("error.jsp").forward(request, response);

		} catch (ServiceException e) {
			logger.error("ServiceException occurred during getting personal data", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Exception occurred during getting personal data");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
