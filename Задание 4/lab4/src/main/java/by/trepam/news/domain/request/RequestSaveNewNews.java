package by.trepam.news.domain.request;

import java.util.ArrayList;
import java.util.HashMap;

import by.trepam.news.controller.CommandName;

public class RequestSaveNewNews implements Request{
	private String title;
	private String name;
	private ArrayList<String> authors;
	private String dateOfIssue;
	private String body;
	private String subcategoryName;
	private String categoryName;

	public CommandName getCommandType() {
		return CommandName.SAVE_NEW_NEWS;
	}

	public HashMap<String,String> getParams() {
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("name", this.name);
		hm.put("dateOfIssue", this.dateOfIssue);
		hm.put("body", this.body);
		hm.put("subcategoryName", this.subcategoryName);
		hm.put("categoryName", this.categoryName);
		int i=0;
		for(String auth:authors){
			hm.put("author"+i, auth);
			i++;
		}
		return hm;
	}

	public void setTitle(String string) {
		title=string;		
	}
	
	public String getTitle( ) {
		return title;		
	}

	public void setParams(Object s) {
		@SuppressWarnings("unchecked")
		HashMap<String,String> params = (HashMap<String,String>)s;
		name=params.get("name");
		dateOfIssue=params.get("dateOfIssue");
		subcategoryName=params.get("subcategoryName");
		categoryName=params.get("categoryName");
		authors=new ArrayList<String>();
		for(int j=0;j<params.size()-4;j++){
			authors.add(params.get("author"+j));	
		}
		
	}

}
