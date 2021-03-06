package by.trepam.like_it.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.trepam.like_it.command.Command;

/**
 * Class of command, that is used to change the locale.
 *
 */

public class LocalizationCommand implements Command {
	private final static LocalizationCommand command = new LocalizationCommand();

	private LocalizationCommand() {
	}

	public static LocalizationCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(CommandConstant.PARAM_LOCAL,
				request.getParameter(CommandConstant.PARAM_LOCAL));
		response.sendRedirect("../like-it");
	}

}
