package by.trepam.like_it.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.trepam.like_it.command.Command;

public class LocalizationCommand implements Command{

	public HttpServletRequest execute(HttpServletRequest request) {
		request.getSession(true).setAttribute("local", request.getParameter("local"));
		return request;
	}

}
