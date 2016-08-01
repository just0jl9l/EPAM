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

public class ChangePersonalAccountCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		AccountService service = factory.getAccountService();
		try {
			Object photo = request.getParameter("photo");
			Object name = request.getParameter("name");
			Object surname = request.getParameter("surname");
			Object accountId = request.getSession(true).getAttribute("account_id");
			if (accountId != null) {
				if (name != null && surname != null && name != "" && surname != "") {
					Account account = new Account();
					account.setId((int) accountId);
					account.setName(name.toString());
					account.setSurname(surname.toString());
					service.updateAccount(account);
					account = service.getAccount((int) accountId);
					request.getSession(true).setAttribute("account", account);
					request.getRequestDispatcher("jsp/personal_account.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding message", e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}
}