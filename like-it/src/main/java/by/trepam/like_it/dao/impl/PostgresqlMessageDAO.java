package by.trepam.like_it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.trepam.like_it.dao.MessageDAO;
import by.trepam.like_it.dao.connection_pool.exception.ConnectionPoolException;
import by.trepam.like_it.dao.connection_pool.impl.PostgresqlConnectionPool;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Message;

public class PostgresqlMessageDAO implements MessageDAO {

	public void insert(Message message, Integer categoryId) throws DAOException {
		String sql = QueryConstant.SQL_INSERT_MESSAGE;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, message.getName());
			stm.setString(2, message.getText());
			stm.setInt(3, categoryId);
			stm.setInt(4, message.getAuthor().getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public void delete(Integer messageId) throws DAOException {
		String sql = QueryConstant.SQL_DELETE_MESSAGE_BY_ID;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, messageId);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}

	}

	public Message getMessage(Integer messageId) throws DAOException {
		String sql = QueryConstant.SQL_GET_MESSAGE_BY_ID;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, messageId);
			ResultSet rs = stm.executeQuery();
			Message message = null;
			if (rs.next()) {
				message = new Message();
				message.setId(messageId);
				message.setName(rs.getString(1));
				message.setText(rs.getString(2));
				message.setAuthor(new Account(rs.getInt(3)));
				message.setDateOfPosting(rs.getTimestamp(4));
			}
			return message;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public List<Message> getAllMessagesOfCategory(Integer categoryId) throws DAOException {
		String sql = QueryConstant.SQL_GET_ALL_MESSAGES_OF_CATEGORY;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, categoryId);
			ResultSet rs = stm.executeQuery();
			List<Message> messages = new ArrayList<Message>();
			Message message = null;
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
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public void update(Message message) throws DAOException {
		String sql = QueryConstant.SQL_UPDATE_MESSAGE;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, message.getName());
			stm.setString(2, message.getText());
			stm.setInt(3, message.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}
}
