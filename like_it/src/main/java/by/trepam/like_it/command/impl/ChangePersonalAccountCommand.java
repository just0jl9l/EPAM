package by.trepam.like_it.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class ChangePersonalAccountCommand implements Command{
	
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		Service service = factory.getService();
		try {
			Object photo = request.getParameter("photo");
			Object name = request.getParameter("name");
			Object surname = request.getSession(true).getAttribute("surname");
			Object accountId = request.getSession(true).getAttribute("account_id");
			if(accountId!=null){
				if(name!= null && surname!=null){
					Account account = new Account((int) accountId);
					account.setName(name.toString());
					account.setSurname(surname.toString());
					service.updateAccount(account);
					account = service.getAccount((Integer)accountId);
					request.getSession(true).setAttribute("account", account);
					request.getRequestDispatcher("jsp/personal_account.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
				}
			}else{
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding message",e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}
}