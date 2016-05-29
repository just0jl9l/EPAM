package by.trepam.news.domain.request;

import by.trepam.news.controller.CommandName;

public interface Request {
	CommandName getCommandType();
	void setTitle(String string);
	String getTitle();
	void setParams(Object params);
}
