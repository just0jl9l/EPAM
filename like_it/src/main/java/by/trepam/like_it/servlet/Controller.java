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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandHandler handler = CommandHandler.getInstance();
		String commandName=request.getParameter("command").toUpperCase();
		System.out.println(commandName);
		Command command = handler.getCommand(commandName);
		if(command!=null){
			request = command.execute(request);
		}else{
			System.out.println("null");
		}
		String url = request.getAttribute("next_page").toString();
		if(url!=null){
			request.getRequestDispatcher(url).forward(request, response);
		}else{
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}
