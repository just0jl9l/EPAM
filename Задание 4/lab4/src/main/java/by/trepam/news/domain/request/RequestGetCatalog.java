package by.trepam.news.domain.request;

import by.trepam.news.controller.CommandName;

public class RequestGetCatalog implements Request{

	private String title;

	public CommandName getCommandType() {
		return CommandName.GET_CATALOG;
	}

	public void setTitle(String string) {
		title=string;		
	}
	
	public String getTitle( ) {
		return title;		
	}

	public void setParams(Object params) {

		System.out.println("RequestGetCatalog setParams");
		return ;
	}

}
