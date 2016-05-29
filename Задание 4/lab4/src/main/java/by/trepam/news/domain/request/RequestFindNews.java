package by.trepam.news.domain.request;

import by.trepam.news.controller.CommandName;
import by.trepam.news.domain.criteria.ICriteria;

public class RequestFindNews implements Request{
	private ICriteria criteria;
	private String title;

	public CommandName getCommandType() {
		return CommandName.FIND_NEWS;
	}
	
	public ICriteria getParams(){
		return criteria;
	}

	public void setTitle(String string) {
		title=string;		
	}
	
	public String getTitle( ) {
		return title;		
	}

	public void setParams(Object params) {
		criteria = (ICriteria) params;
		
	}

}
