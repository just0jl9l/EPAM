package by.trepam.like_it.command.impl.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.impl.CommandConstant;

public class GotoChangePersonalAccountCommand implements Command {

	private final static GotoChangePersonalAccountCommand command = new GotoChangePersonalAccountCommand();

	private GotoChangePersonalAccountCommand() {
	}

	public static GotoChangePersonalAccountCommand getInstance() {
		return command;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(CommandConstant.PARAM_CHANGE, CommandConstant.TRUE);
		request.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(request, response);
	}

}