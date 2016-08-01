package by.trepam.like_it.command.impl.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.like_it.command.Command;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.CategoryService;
import by.trepam.like_it.service.MessageService;
import by.trepam.like_it.service.exception.ServiceException;
import by.trepam.like_it.service.factory.ServiceFactory;

public class AddMessageCommand implements Command {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		MessageService messageService = factory.getMessageService();
		CategoryService categoryService = factory.getCategoryService();
		try {
			Object title = request.getParameter("title");
			Object text = request.getParameter("text");
			Object category = request.getSession(true).getAttribute("category");
			Object account_id = request.getSession(true).getAttribute("account_id");
			if (account_id != null) {
				if (category != null) {
					if (title != null && text != null) {
						Message message = new Message();
						message.setName(title.toString());
						message.setText(text.toString());
						message.setAuthor(new Account((int) account_id));
						messageService.addMessage(message, ((Category) category).getId());
						category = categoryService.getCategory(((Category) category).getId(),
								request.getSession(true).getAttribute("local"));
						if (category != null) {
							request.getSession(true).setAttribute("category", category);
							request.getSession(true).setAttribute("messages", ((Category) category).getMessages());
						}
					}
					request.getRequestDispatcher("jsp/category.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("ServiceException occurred during adding message", e);
			request.getRequestDispatcher("jsp/like_it.jsp").forward(request, response);
		}
	}
}
