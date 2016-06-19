package by.trepam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import by.trepam.dao.MessageDAO;
import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Account;
import by.trepam.domain.Message;

public class PostgresqlMessageDAO implements MessageDAO{
	
	Connection connection;

	public void insert(Message message,int categoryID)  throws DAOException {
		try(Connection connection = PostgresqlDAOFactory.createConnection()) {
			String sql = QueryConstants.INSERT_MESSAGE;
			PreparedStatement stm;
			stm = connection.prepareStatement(sql);
			stm.setInt(1, message.getId());
			stm.setString(2, message.getName());
			stm.setString(3, message.getText());
			stm.setInt(4, message.getAuthor().getId());
			stm.setInt(5, categoryID);
			stm.setTimestamp(6, new Timestamp(message.getDateOfPosting().getTime()));
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void delete(int messageID) throws DAOException {
		try(Connection connection = PostgresqlDAOFactory.createConnection()) {
			String sql = QueryConstants.DELETE_MESSAGE_BY_ID;
			PreparedStatement stm;
			stm = connection.prepareStatement(sql);
			stm.setInt(1, messageID);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
		
	}

	public Message getMessage(int messageID)  throws DAOException {
		Connection connection = PostgresqlDAOFactory.createConnection();
		String sql = QueryConstants.GET_MESSAGE_BY_ID;
		Message message = null;
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(sql);
			stm.setInt(1, messageID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				message = new Message();
				message.setId(rs.getInt(1));
				message.setName(rs.getString(2));
				message.setText(rs.getString(3));
				message.setAuthor(new Account(rs.getInt(4)));
				message.setDateOfPosting(rs.getTimestamp(5));
			}
			rs.close();
			stm.close();
			connection.close();
			return message;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public List<Message> getAllMessagesOfCategory(int categoryID) throws DAOException {
		Connection connection = PostgresqlDAOFactory.createConnection();
		String sql = QueryConstants.GET_ALL_MESSAGES_OF_CATEGORY;
		List<Message> messages = new ArrayList<Message>();
		Message message = null;
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(sql);
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
			rs.close();
			stm.close();
			connection.close();
			return messages;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void update(Message message,int categoryID) throws DAOException {
		try {
			Connection connection = PostgresqlDAOFactory.createConnection();
			String sql = QueryConstants.UPDATE_MESSAGE;
			PreparedStatement stm;
			stm = connection.prepareStatement(sql);
			stm.setString(1, message.getName());
			stm.setString(2, message.getText());
			stm.setInt(3, message.getAuthor().getId());
			stm.setTimestamp(4, new Timestamp(message.getDateOfPosting().getTime()));
			stm.setInt(5, categoryID);
			stm.setInt(6, message.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}
}
