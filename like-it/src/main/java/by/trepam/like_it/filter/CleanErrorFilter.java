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

public class CleanErrorFilter implements Filter {

	private ArrayList<String> avoidList;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getServletPath();
		HttpSession session = request.getSession(false);
		if (null != session && !avoidList.contains(url)){
			session.setAttribute(CommandConstant.PARAM_ERROR, null);			
			session.setAttribute(CommandConstant.PARAM_PASSWORD_ERROR,null);
			session.setAttribute(CommandConstant.PARAM_NOT_ALL_DATA_ERROR,null);
			session.setAttribute(CommandConstant.PARAM_LOGIN_ERROR,null);
		}
		chain.doFilter(request, response);		
	}

	public void init(FilterConfig config) throws ServletException {
		String comms = config.getInitParameter("avoid");
		StringTokenizer token = new StringTokenizer(comms, ",");

		avoidList = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			avoidList.add(token.nextToken());
		}
	}
}
