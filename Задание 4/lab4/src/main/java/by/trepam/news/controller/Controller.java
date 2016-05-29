package by.trepam.news.controller;

import by.trepam.news.command.Command;
import by.trepam.news.domain.request.Request;
import by.trepam.news.domain.response.Response;

public class Controller {
	CommandHelper helper = new CommandHelper();

	public Response doAction(Request request){
		System.out.println("Controller doAction");
		Command command = helper.getCommand(request.getCommandType().name());
		Response response = command.execute(request);
		return response;
	 }
}
