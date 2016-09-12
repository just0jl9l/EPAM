package by.trepam.like_it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.connection_pool.exception.ConnectionPoolException;
import by.trepam.like_it.dao.connection_pool.impl.PostgresqlConnectionPool;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Answer;

public class PostgresqlAnswerDAO implements AnswerDAO{

	public void insert(Answer answer,Integer messageId)  throws DAOException {
		String sql = QueryConstant.SQL_INSERT_ANSWER;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, messageId);
			stm.setString(2, answer.getText());
			stm.setTimestamp(3, new Timestamp(answer.getDateOfPosting().getTime()));
			stm.setInt(4, answer.getAuthor().getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public void delete(Integer answerId)  throws DAOException {
		String sql = QueryConstant.SQL_DELETE_ANSWER_BY_ID;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerId);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
		
	}

	public Answer getAnswer(Integer answerId) throws DAOException {
		String sql = QueryConstant.SQL_GET_ANSWER_BY_ID;
		Answer answer = null;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerId);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				answer = new Answer();
				answer.setText(rs.getString(1));
				answer.setDateOfPosting(rs.getTimestamp(2));
				answer.setAuthor(new Account(rs.getInt(3)));
				answer.setId(answerId);
			}
			return answer;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public List<Answer> getAllAnswersOfMessage(Integer messageId)  throws DAOException {
		String sql = QueryConstant.SQL_GET_ALL_ANSWER_OF_MESSAGE;
		List<Answer> answers = new ArrayList<Answer>();
		Answer answer = null;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, messageId);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				answer = new Answer();
				answer.setId(rs.getInt(1));
				answer.setText(rs.getString(2));
				answer.setDateOfPosting(rs.getTimestamp(3));
				answer.setAuthor(new Account(rs.getInt(4)));
				answers.add(answer);
			}
			return answers;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public void update(Answer answer,Integer messageId) throws DAOException {
		String sql = QueryConstant.SQL_UPDATE_ANSWER;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, messageId);
			stm.setString(2, answer.getText());
			stm.setTimestamp(3, new Timestamp(answer.getDateOfPosting().getTime()));
			stm.setInt(4, answer.getAuthor().getId());
			stm.setInt(5, answer.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public double rating(Integer answerId) throws DAOException {
		String sql = QueryConstant.SQL_ANSWER_RATING_BY_ID;
		double rating = -1;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerId);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				rating=rs.getDouble(1);
			}
			return rating;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

}
