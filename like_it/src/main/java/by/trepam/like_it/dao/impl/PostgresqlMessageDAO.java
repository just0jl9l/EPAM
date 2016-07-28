package by.trepam.like_it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import by.trepam.like_it.dao.MessageDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Message;

public class PostgresqlMessageDAO implements MessageDAO{

	public void insert(Message message,int categoryID)  throws DAOException {
		String sql = QueryConstants.INSERT_MESSAGE;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, message.getName());
			stm.setString(2, message.getText());
			stm.setInt(3, categoryID);
			stm.setInt(4, message.getAuthor().getId());
			stm.setTimestamp(5, new Timestamp(message.getDateOfPosting().getTime()));
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void delete(int messageID) throws DAOException {
		String sql = QueryConstants.DELETE_MESSAGE_BY_ID;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, messageID);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
		
	}

	public Message getMessage(int messageID)  throws DAOException {
		String sql = QueryConstants.GET_MESSAGE_BY_ID;
		Message message = null;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, messageID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				message = new Message();
				message.setId(messageID);
				message.setName(rs.getString(1));
				message.setText(rs.getString(2));
				message.setAuthor(new Account(rs.getInt(3)));
				message.setDateOfPosting(rs.getTimestamp(4));
			}
			return message;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public List<Message> getAllMessagesOfCategory(int categoryID) throws DAOException {
		String sql = QueryConstants.GET_ALL_MESSAGES_OF_CATEGORY;
		List<Message> messages = new ArrayList<Message>();
		Message message = null;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, categoryID);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				message = new Message();
				message.setId(rs.getInt(1));
				message.setName(rs.getString(2));
				message.setText(rs.getString(3));
				message.setAuthor(new Account(rs.getInt(4)));
				message.setDateOfPosting(rs.getTimestamp(5));
				messages.add(message);
			}
			return messages;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void update(Message message) throws DAOException {
		String sql = QueryConstants.UPDATE_MESSAGE;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, message.getName());
			stm.setString(2, message.getText());
			stm.setInt(3, message.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}
}
