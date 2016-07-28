package by.trepam.like_it.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class RegistrationCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		Service service = factory.getService();
		try {
			Object photo = request.getParameter("photo");
			Object login = request.getParameter("login");
			Object name = request.getParameter("name");
			Object surname = request.getParameter("surname");
			Object status = request.getParameter("status");
			Object password = request.getParameter("password");
			Object password2 = request.getParameter("password2");
			if (service.isLoginFree(login.toString())) {
				boolean isOk = !"".equals(password) && !"".equals(password2) && !"".equals(name) && !"".equals(surname)
						&& password != null && password2 != null && name != null && surname != null;
				if (isOk) {
					if (password.equals(password2)) {
						Account account = new Account();
						account.setLogin(login.toString());
						account.setName(name.toString());
						account.setSurname(surname.toString());
						account.setPassword(password.toString());
						if ("Administrator".equals(status) || "Администратор".equals(status)) {
							account.setStatus("admin");
						} else {
							account.setStatus("client");
						}
						service.addAccount(account);
						account = service.logIn(login.toString(), password.toString());
						request.getSession(true).setAttribute("account_id", account.getId());
						request.getSession(true).setAttribute("status", status);
						request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
					} else {
						request.setAttribute("password_error", "yes");
						request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("not_all_error", "yes");
					request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("login_error", "yes");
				request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
			}

		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding answer", e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}