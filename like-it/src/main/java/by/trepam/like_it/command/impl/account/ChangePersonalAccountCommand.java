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
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;
import by.trepam.like_it.service.impl.AccountServiceImpl;

public class ChangePersonalAccountCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private final static ChangePersonalAccountCommand command = new ChangePersonalAccountCommand();

	private ChangePersonalAccountCommand() {
	}

	public static ChangePersonalAccountCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter(CommandConstant.PARAM_NAME);
			String surname = request.getParameter(CommandConstant.PARAM_SURNAME);
			Integer accountId = (Integer) request.getSession(true).getAttribute(CommandConstant.PARAM_ACCOUNT_ID);
			if (name != null && surname != null && !CommandConstant.EMPTY.equals(name)
					&& !CommandConstant.EMPTY.equals(surname)) {
				Account account = new Account();
				account.setId(accountId);
				account.setName(name);
				account.setSurname(surname);
				AccountService service = AccountServiceImpl.getInstance();
				service.updateAccount(account);
				GetPersonalAccountCommand getCommand = GetPersonalAccountCommand.getInstance();
				getCommand.execute(request, response);
			} else {
				request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account data");
				response.sendRedirect("../like-it/error");
			}
		} catch (NumberFormatException e) {
			logger.error("Wrong account id", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account id");
			response.sendRedirect("../like-it/error");
		} catch (GettingDataException e) {
			logger.error("GettingDataException occurred during adding message", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR,
					"Exception occurred during adding message");
			response.sendRedirect("../like-it/error");
		} catch (WrongDataException e) {
			logger.error("Wrong account", e);
			request.getSession(true).setAttribute(CommandConstant.PARAM_ERROR, "Wrong account");
			response.sendRedirect("../like-it/error");
		}
	}
}