package by.trepam.like_it.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class LoginCommand implements Command{
	
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public HttpServletRequest execute(HttpServletRequest request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		Service service = factory.getService();
		try {
			Account account = service.logIn(request.getParameter("login"),request.getParameter("password"));
			if(account!=null){
				request.getSession(true).setAttribute("account_id", account.getId());
				request.getSession(true).setAttribute("status", account.getStatus());
				request.setAttribute("next_page", "jsp/like_it.jsp");
			}
		} catch (ServiceException e) {
			request.setAttribute("next_page", "jsp/login.jsp");
			logger.error("ServiceException occurred during logination",e);
		}
		return request;
	}

}
