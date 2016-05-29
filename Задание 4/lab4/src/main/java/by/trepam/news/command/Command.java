package by.trepam.news.command;

import by.trepam.news.domain.request.Request;
import by.trepam.news.domain.response.Response;

public interface Command {
	Response execute(Request request);

}
