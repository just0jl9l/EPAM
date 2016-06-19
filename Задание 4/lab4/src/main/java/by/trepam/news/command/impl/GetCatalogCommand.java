package by.trepam.news.command.impl;

import by.trepam.news.command.Command;
import by.trepam.news.domain.request.Request;
import by.trepam.news.domain.response.Response;
import by.trepam.news.domain.response.ResponseGetCatalog;
import by.trepam.news.service.IService;
import by.trepam.news.service.ServiceFactory;
import by.trepam.news.service.exception.ServiceException;

public class GetCatalogCommand implements Command{
	public Response execute(Request request){
		ResponseGetCatalog response = new ResponseGetCatalog();
		ServiceFactory factory = ServiceFactory.getInstance();
		IService service = factory.getNewsService();
		try{			
			response.setStatus(true);
			response.setCatalog(service.getCatalog());
			response.setMessage("everything is ok with request "+request.getTitle());
		}catch(ServiceException e){
			response.setStatus(false);
			response.setMessage("something went wrong with request "+request.getTitle());
		}
		return response;
	}
}
