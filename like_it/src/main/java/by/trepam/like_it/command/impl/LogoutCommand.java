package by.trepam.like_it.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.trepam.like_it.command.Command;

public class LogoutCommand implements Command{

	public HttpServletRequest execute(HttpServletRequest request) {
		request.getSession(true).setAttribute("account_id", null);
		request.getSession(true).setAttribute("status", null);
		request.setAttribute("next_page", "jsp/like_it.jsp");
		return request;
	}

}
