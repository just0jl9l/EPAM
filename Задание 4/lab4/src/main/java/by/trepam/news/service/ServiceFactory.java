package by.trepam.news.service;

import by.trepam.news.service.impl.NewsServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory factory = new ServiceFactory();
	private IService newsService = new NewsServiceImpl();

	public static ServiceFactory getInstance() {
		return factory;
	}	 

	public IService getNewsService() {
		System.out.println("ServiceFactory getNewsService");
		return newsService;
	}
}
