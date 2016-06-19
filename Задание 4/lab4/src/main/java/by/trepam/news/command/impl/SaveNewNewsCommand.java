package by.trepam.news.command.impl;

import by.trepam.news.command.Command;
import by.trepam.news.domain.request.Request;
import by.trepam.news.domain.request.RequestSaveNewNews;
import by.trepam.news.domain.response.Response;
import by.trepam.news.domain.response.ResponseSaveNewNews;
import by.trepam.news.service.IService;
import by.trepam.news.service.ServiceFactory;
import by.trepam.news.service.exception.ServiceException;

public class SaveNewNewsCommand implements Command{
	public Response execute(Request request){
		RequestSaveNewNews req = (RequestSaveNewNews) request;
		Response response = new ResponseSaveNewNews();
			ServiceFactory factory = ServiceFactory.getInstance();
			IService service = factory.getNewsService();
			try{
				service.saveNewNews(req.getParams());
				response.setStatus(true);
				response.setMessage("everything is ok with "+request.getTitle());
			}catch(ServiceException e){
				// logging
				response.setStatus(false);
				response.setMessage("something went wrong with "+request.getTitle());
				e.printStackTrace();
			}
		
		return response;
	}
}