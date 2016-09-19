package by.trepam.like_it.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface class, all command objects implement the methods of this class.
 */

public interface Command {

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
