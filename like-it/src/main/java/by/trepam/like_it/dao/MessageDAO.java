package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Message;

public interface MessageDAO {

	void insert(Message message,Integer categoryID) throws DAOException;
	void delete(Integer messageID) throws DAOException;
	Message getMessage(Integer messageID) throws DAOException;
	List<Message> getAllMessagesOfCategory(Integer categoryID) throws DAOException;
	void update(Message message) throws DAOException;
}
