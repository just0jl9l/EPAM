package by.trepam.news.command.impl;

import by.trepam.news.command.Command;
import by.trepam.news.domain.request.Request;
import by.trepam.news.domain.request.RequestFindNews;
import by.trepam.news.domain.response.Response;
import by.trepam.news.domain.response.ResponseFindNews;
import by.trepam.news.service.IService;
import by.trepam.news.service.ServiceFactory;
import by.trepam.news.service.exception.ServiceException;

public class FindNewsCommand implements Command{
	public Response execute(Request request){
		RequestFindNews req = (RequestFindNews) request;
		ResponseFindNews response = new ResponseFindNews();
		ServiceFactory factory = ServiceFactory.getInstance();
		IService service = factory.getNewsService();
		try{
			response.setNews(service.findNews(req.getParams()));
			response.setStatus(true);
			response.setMessage("everything is ok with request "+req.getTitle());
		}catch(ServiceException e){
			response.setStatus(false);
			response.setMessage("something went wrong with request "+req.getTitle());
		}
		return response;
	}
}