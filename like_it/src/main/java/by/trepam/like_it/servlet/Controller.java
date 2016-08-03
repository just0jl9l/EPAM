package by.trepam.like_it.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.command.CommandHandler;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		CommandHandler handler = CommandHandler.getInstance();
		String commandName = request.getParameter("command").toUpperCase().replaceAll("-", "_");
		Command command = handler.getCommand(commandName);
		if (command != null) {
			command.execute(request, response);
		} else {
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}
