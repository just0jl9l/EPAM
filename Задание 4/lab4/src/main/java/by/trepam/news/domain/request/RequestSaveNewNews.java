package by.trepam.news.domain.request;

import by.trepam.news.controller.CommandName;

public class RequestSaveNewNews implements Request{
	private String title;
	private boolean status;
	private String name;
	private String[] authors;
	private String dateOfIssue;
	private String body;
	private String subcategoryName;
	private String categoryName;

	public CommandName getCommandType() {
		return CommandName.SAVE_NEW_NEWS;
	}
	
	public boolean isOK(){

		return status;
	}

	public String[][] getParams() {
		String[][] s={{name},authors,{dateOfIssue},{body},{subcategoryName},{categoryName}};
		return s;
	}

	public void setTitle(String string) {
		title=string;		
	}
	
	public String getTitle( ) {
		return title;		
	}

	public void setParams(Object params) {
		String[][] news = (String[][]) params;
		if(news.length==6){
			name = news[0][0];
			authors=news[1];
			dateOfIssue=news[2][0];
			body=news[3][0];
			subcategoryName=news[4][0];
			categoryName=news[5][0];
			status=true;
			
		}else{
			status=false;
		}
		
	}

}
