package by.trepam.like_it.service;

import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

public interface MessageService {		
	Message getMessage(Integer messageId) throws WrongDataException, GettingDataException, DataNotFoundException;
	void addMessage(Message message, Integer categoryId) throws GettingDataException, WrongDataException;
	void updateMessage(Message message, String title, String text) throws GettingDataException, WrongDataException;
	void deleteMessage(Integer messageId) throws GettingDataException, WrongDataException;
}
