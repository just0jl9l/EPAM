package by.trepam.news.domain.response;

import by.trepam.news.controller.CommandName;

public class ResponseSaveNewNews implements Response{
	private boolean status;
	private String message;

	public CommandName getCommandType() {
		return CommandName.SAVE_NEW_NEWS;
	}

	public void setStatus(boolean b) {
		status=b;		
	}

	public void setMessage(String string) {
		message=string;
	}

	public boolean isCommandExecuted() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
