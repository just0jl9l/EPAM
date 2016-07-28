package by.trepam.like_it.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Message;

public class GotoChangeMessageCommand implements Command{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Message message = (Message) request.getSession(true).getAttribute("message");
		if(message!=null){
			request.setAttribute("change", "yes");
			request.getRequestDispatcher("jsp/add_message.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}

}