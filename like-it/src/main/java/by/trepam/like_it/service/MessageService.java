package by.trepam.like_it.service;

import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.exception.ServiceException;

public interface MessageService {	
	
	Message getMessage(Integer message_id) throws ServiceException;
	void addMessage(Message message, Integer id_category) throws ServiceException;
	void updateMessage(Message message) throws ServiceException;
	void deleteMessage(Integer id_message) throws ServiceException;
}
