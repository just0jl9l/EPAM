package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Message;

public interface MessageDAO {

	void insert(Message message,Integer categoryId) throws DAOException;
	void delete(Integer messageId) throws DAOException;
	Message getMessage(Integer messageId) throws DAOException;
	List<Message> getAllMessagesOfCategory(Integer categoryId) throws DAOException;
	void update(Message message) throws DAOException;
}
