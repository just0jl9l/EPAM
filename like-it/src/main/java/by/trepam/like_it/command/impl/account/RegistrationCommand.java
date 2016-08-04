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

public class RegistrationCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static RegistrationCommand command = new RegistrationCommand();

	private RegistrationCommand() {
	}

	public static RegistrationCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountService service = AccountServiceImpl.getInstance();
		try {
			Object photo = request.getParameter(CommandConstant.PARAM_PHOTO);
			String login = request.getParameter(CommandConstant.PARAM_LOGIN);
			String name = request.getParameter(CommandConstant.PARAM_NAME);
			String surname = request.getParameter(CommandConstant.PARAM_SURNAME);
			String status = request.getParameter(CommandConstant.PARAM_STATUS);
			String password = request.getParameter(CommandConstant.PARAM_PASSWORD);
			String secondPassword = request.getParameter(CommandConstant.PARAM_SECOND_PASSWORD);
			if (service.isLoginFree(login)) {
				boolean isOk = !CommandConstant.EMPTY.equals(password) && !CommandConstant.EMPTY.equals(secondPassword)
						&& !CommandConstant.EMPTY.equals(name) && !CommandConstant.EMPTY.equals(surname)
						&& password != null && secondPassword != null && name != null && surname != null;
				if (isOk) {
					if (password.equals(secondPassword)) {
						Account account = new Account();
						account.setLogin(login);
						account.setName(name);
						account.setSurname(surname);
						account.setPassword(password);
						if (CommandConstant.ADMIN_NAME_EN.equals(status)
								|| CommandConstant.ADMIN_NAME_RU.equals(status)) {
							account.setStatus(CommandConstant.STATUS_ADMIN);
						} else {
							account.setStatus(CommandConstant.STATUS_CLIENT);
						}
						service.addAccount(account);
						account = service.logIn(login,password);
						if (account != null) {
							request.getSession(true).setAttribute(CommandConstant.PARAM_ACCOUNT_ID, account.getId());
							request.getSession(true).setAttribute(CommandConstant.PARAM_STATUS, account.getStatus());
							response.sendRedirect("../like-it");
						} else {
							request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, CommandConstant.TRUE);
							response.sendRedirect("../like-it/login");
						}
					} else {
						request.getSession(true).setAttribute(CommandConstant.PARAM_PASSWORD_ERROR,
								CommandConstant.TRUE);
						response.sendRedirect("../like-it/registration");
					}
				} else {
					request.getSession(true).setAttribute(CommandConstant.PARAM_NOT_ALL_DATA_ERROR,
							CommandConstant.TRUE);
					response.sendRedirect("../like-it/registration");
				}
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_LOGIN_ERROR, CommandConstant.TRUE);
				response.sendRedirect("../like-it/registration");
			}

		} catch (ServiceException e) {
			logger.error("ServiceException occurred during registration", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during registration");
			response.sendRedirect("../like-it/error");
		}
	}

}