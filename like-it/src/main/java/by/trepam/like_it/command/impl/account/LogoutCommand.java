package by.trepam.like_it.command.impl.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;

/**
 * Class of command, that is used to log out.
 *
 */

public class LogoutCommand implements Command {
	private final static LogoutCommand command = new LogoutCommand();

	private LogoutCommand() {
	}

	public static LogoutCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(CommandConstant.PARAM_ACCOUNT_ID, null);
		request.getSession(true).setAttribute(CommandConstant.PARAM_STATUS, null);
		response.sendRedirect("../like-it");
	}

}
