package by.trepam.like_it.command.impl.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.AccountService;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class GetPersonalAccountCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		AccountService service = factory.getAccountService();
		try {
			Object accountID = request.getSession(true).getAttribute("account_id");
			if (accountID != null) {
				Account account = service.getAccount((Integer) accountID);
				request.getSession(true).setAttribute("account", account);
				request.getRequestDispatcher("jsp/personal_account.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during getting personal data", e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}
