package by.trepam.like_it.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.trepam.like_it.command.impl.CommandConstant;

public class SessionFilter implements Filter {

	private ArrayList<String> commands;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String command = request.getParameter("command");
		HttpSession session = request.getSession(false);
		if (commands.contains(command)
				&& (null == session || null == session.getAttribute(CommandConstant.PARAM_ACCOUNT_ID))) {
			response.sendRedirect("../like-it/login");
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		String comms = config.getInitParameter("commands");
		StringTokenizer token = new StringTokenizer(comms, ",");

		commands = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			commands.add(token.nextToken());
		}
	}
}
