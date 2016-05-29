package by.trepam.news.domain.response;

import by.trepam.news.controller.CommandName;
import by.trepam.news.domain.News;

public class ResponseFindNews implements Response{
	private boolean status;
	private String message;
	private News news;

	public CommandName getCommandType() {
		return CommandName.FIND_NEWS;
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

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news=news;
	}

}
