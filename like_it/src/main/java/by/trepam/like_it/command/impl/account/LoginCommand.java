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
import by.trepam.like_it.service.factory.ServiceFactory;

public class LoginCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		AccountService service = factory.getAccountService();
		try {
			Account account = service.logIn(request.getParameter("login"), request.getParameter("password"));
			if (account != null) {
				request.getSession(true).setAttribute("error_message", null);
				request.getSession(true).setAttribute("account_id", account.getId());
				request.getSession(true).setAttribute("status", account.getStatus());
				request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
			} else {
				request.setAttribute("error_message", CommandConstant.TRUE);
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during logination", e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}
