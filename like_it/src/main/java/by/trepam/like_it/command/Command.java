package by.trepam.like_it.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

	HttpServletRequest execute(HttpServletRequest request);
}
