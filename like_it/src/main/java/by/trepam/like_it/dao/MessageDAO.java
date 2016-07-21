package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Message;

public interface MessageDAO {

	void insert(Message message,int categoryID) throws DAOException;
	void delete(int messageID) throws DAOException;
	Message getMessage(int messageID) throws DAOException;
	List<Message> getAllMessagesOfCategory(int categoryID) throws DAOException;
	void update(Message message,int categoryID) throws DAOException;
}
