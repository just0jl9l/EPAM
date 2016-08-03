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

public class SessionFilter implements Filter {

	private ArrayList<String> commands;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String command = request.getParameter("command");
		if (commands.contains(command)) {
			HttpSession session = request.getSession(false);
			if (null == session || null == session.getAttribute("account-id")){
				response.sendRedirect("../like-it/login");
			} else {
	            chain.doFilter(request, response);
	       }
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
