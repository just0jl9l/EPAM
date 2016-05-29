package by.trepam.news.domain.response;

import by.trepam.news.controller.CommandName;
import by.trepam.news.domain.Catalog;

public class ResponseGetCatalog implements Response{
	private boolean status;
	private String message;
	private Catalog catalog;

	public CommandName getCommandType() {
		return CommandName.GET_CATALOG;
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

	public void setCatalog(Catalog c) {
		catalog=c;		
	}

	public Catalog getCatalog() {
		return catalog;
	}

}
