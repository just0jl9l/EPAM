package by.trepam.like_it.service.factory;

import by.trepam.like_it.service.AccountService;
import by.trepam.like_it.service.AnswerService;
import by.trepam.like_it.service.CategoryService;
import by.trepam.like_it.service.MessageService;
import by.trepam.like_it.service.impl.AccountServiceImpl;
import by.trepam.like_it.service.impl.AnswerServiceImpl;
import by.trepam.like_it.service.impl.CategoryServiceImpl;
import by.trepam.like_it.service.impl.MessageServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory factory = new ServiceFactory();
	private AccountService accountService = new AccountServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private MessageService messageService = new MessageServiceImpl();
	private AnswerService answerService = new AnswerServiceImpl();
	
	private ServiceFactory(){}

	public static ServiceFactory getInstance() {
		return factory;
	}	 

	public AccountService getAccountService() {
		return accountService;
	}	 

	public CategoryService getCategoryService() {
		return categoryService;
	}	 

	public MessageService getMessageService() {
		return messageService;
	}	 

	public AnswerService getAnswerService() {
		return answerService;
	}
	
	
}
