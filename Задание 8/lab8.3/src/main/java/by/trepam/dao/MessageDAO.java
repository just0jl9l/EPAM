package by.trepam.dao;

import java.util.List;

import by.trepam.dao.exception.DAOException;
import by.trepam.domain.Message;

public interface MessageDAO {

	void insert(Message message,int categoryID) throws DAOException;
	void delete(int messageID) throws DAOException;
	Message getMessage(int messageID) throws DAOException;
	List<Message> getAllMessagesOfCategory(int categoryID) throws DAOException;
	void update(Message message,int categoryID) throws DAOException;
}
