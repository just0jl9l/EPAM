package by.trepam.like_it.command.impl.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.trepam.like_it.command.Command;

public class GotoChangePersonalAccountCommand implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object accountId = request.getSession(true).getAttribute("account_id");
		if (accountId != null) {
			request.setAttribute("change", "yes");
			request.getRequestDispatcher("jsp/registration.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
		}
	}

}