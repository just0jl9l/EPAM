package by.trepam.like_it.service;

import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

/**
 * Interface class of service layer. It provides set of available operations on
 * messages.
 *
 */

public interface MessageService {

	/**
	 * Method returns existing message or null, if message with messageId does
	 * not exist.
	 * 
	 * @param messageId
	 * @return
	 * @throws WrongDataException
	 * @throws GettingDataException
	 * @throws DataNotFoundException
	 */
	Message getMessage(Integer messageId) throws WrongDataException, GettingDataException, DataNotFoundException;

	/**
	 * Method inserts new message data of category with categoryId.
	 * 
	 * @param message
	 * @param categoryId
	 * @throws GettingDataException
	 * @throws WrongDataException
	 */
	void addMessage(Message message, Integer categoryId) throws GettingDataException, WrongDataException;

	/**
	 * Method updates message data, if this message exists.
	 * 
	 * @param message
	 * @param title
	 * @param text
	 * @throws GettingDataException
	 * @throws WrongDataException
	 */
	void updateMessage(Message message, String title, String text) throws GettingDataException, WrongDataException;

	/**
	 * Method deletes message data, if message with messageId exists.
	 * 
	 * @param messageId
	 * @throws GettingDataException
	 * @throws WrongDataException
	 */
	void deleteMessage(Integer messageId) throws GettingDataException, WrongDataException;
}
