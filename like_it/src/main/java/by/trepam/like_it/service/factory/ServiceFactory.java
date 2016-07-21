package by.trepam.like_it.service.factory;

import by.trepam.like_it.service.Service;
import by.trepam.like_it.service.impl.LikeItService;

public class ServiceFactory {
	private static final ServiceFactory factory = new ServiceFactory();
	private Service newsService = new LikeItService();

	public static ServiceFactory getInstance() {
		return factory;
	}	 

	public Service getService() {
		return newsService;
	}
}
