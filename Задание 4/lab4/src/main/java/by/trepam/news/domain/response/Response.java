package by.trepam.news.domain.response;

import by.trepam.news.controller.CommandName;

public interface Response {
	CommandName getCommandType();
	void setStatus(boolean b);
	void setMessage(String string);
	boolean isCommandExecuted();
	String getMessage();

}
