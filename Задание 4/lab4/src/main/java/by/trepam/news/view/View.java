package by.trepam.news.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import by.trepam.news.controller.CommandName;
import by.trepam.news.domain.criteria.CriteriaAuthorDate;
import by.trepam.news.domain.criteria.CriteriaNameAuthor;
import by.trepam.news.domain.criteria.CriteriaNameDate;
import by.trepam.news.domain.request.Request;
import by.trepam.news.domain.request.RequestFindNews;
import by.trepam.news.domain.request.RequestGetCatalog;
import by.trepam.news.domain.request.RequestSaveNewNews;
import by.trepam.news.domain.response.Response;
import by.trepam.news.domain.response.ResponseFindNews;
import by.trepam.news.domain.response.ResponseGetCatalog;
import by.trepam.news.domain.response.ResponseSaveNewNews;

public class View {
	public Request start() throws NumberFormatException, IOException{
		Request req=null;
		System.out.println("Select an action:\n1-show catalog\n2-find news\n3-add new news");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		switch(Integer.parseInt(in.readLine())){
		case 1:{
			req=doUserAction(CommandName.GET_CATALOG,null);
		}break;
		case 2:{
			System.out.println("Select search criterion:\n1-news name and date of issue;\n2-author and date of issue\n3-news name and author;");
			switch(Integer.parseInt(in.readLine())){
			case 1:{
				System.out.println("Enter news name");
				String name = in.readLine();
				System.out.println("Enter date of issue");
				String date = in.readLine();
				CriteriaNameDate cr = new CriteriaNameDate();
				cr.setDate(date);
				cr.setName(name);
				req=doUserAction(CommandName.FIND_NEWS,cr);
			}break;
			case 2:{
				System.out.println("Enter author");
				String author = in.readLine();
				System.out.println("Enter date of issue");
				String date = in.readLine();
				CriteriaAuthorDate cr = new CriteriaAuthorDate();
				cr.setDate(date);
				cr.setAuthor(author);
				req=doUserAction(CommandName.FIND_NEWS,cr);
			}break;
			case 3:{
				System.out.println("Enter news name");
				String name = in.readLine();
				System.out.println("Enter author");
				String author = in.readLine();
				CriteriaNameAuthor cr = new CriteriaNameAuthor();
				cr.setName(name);
				cr.setAuthor(author);
				req=doUserAction(CommandName.FIND_NEWS,cr);
			}break;
			}
		}break;
		case 3:{
			System.out.println("Enter news name");
			String name=(in.readLine());
			System.out.println("Enter authors (seperator - ;)");
			String providers=(in.readLine());
			System.out.println("Enter data of issue");
			String dateOfIssue=(in.readLine());
			System.out.println("Enter news body");
			String body=(in.readLine());
			System.out.println("Enter news category name");
			String categoryN = in.readLine();
			System.out.println("Enter news subcategory name");
			String subcategoryN = in.readLine();
			List<String> authors=new ArrayList<String>();
			int begin = providers.indexOf(';');
			while (begin > 0) {
				authors.add(providers.substring(0, begin).trim());
				providers = providers.substring(begin + 1);
				begin = providers.indexOf(';');
			}
			String[]  str = new String[authors.size()];
			str = authors.toArray(str);
			String[][] s={{name},str,{dateOfIssue},{body},{subcategoryN},{categoryN}};
			req=doUserAction(CommandName.SAVE_NEW_NEWS,s);
		}break;
		}
		
		return req;		
	}
	
	public Request doUserAction(CommandName c,Object params) {
		System.out.println("View doUserAction");
		Request request=null;
		switch(c){
		case SAVE_NEW_NEWS:{
			request = new RequestSaveNewNews();
			request.setParams(params);
			request.setTitle("save new news");
		}break;
		case FIND_NEWS:{
			request = new RequestFindNews();
			request.setParams(params);
			request.setTitle("find news");
		}break;
		case GET_CATALOG:{
			request = new RequestGetCatalog();
			request.setParams(params);
			request.setTitle("get catalog");
		}break;
		}
		return request;
	}
	
	public void printAnswer(Response response){
		System.out.println("View printAnswer");
		 switch(response.getCommandType()){
		 case SAVE_NEW_NEWS:{
			 	ResponseSaveNewNews resp = (ResponseSaveNewNews) response;
			 	System.out.println(resp.getMessage());
			}break;
			case FIND_NEWS:{
				ResponseFindNews resp = (ResponseFindNews) response;
			 	System.out.println(resp.getMessage());
			 	System.out.println(resp.getNews().show());
			}break;
			case GET_CATALOG:{
				ResponseGetCatalog resp = (ResponseGetCatalog) response;
			 	System.out.println(resp.getMessage());
			 	System.out.println(resp.getCatalog().show());
			}break;
		 }
	}
//
//	public void printAnswer(String message){
//		 
//	}
}
