package by.trepam.like_it.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.trepam.like_it.command.Command;

public class LogoutCommand implements Command{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute("account_id", null);
		request.getSession(true).setAttribute("status", null);
		request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
	}

}
