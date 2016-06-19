package by.trepam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import by.trepam.dao.AnswerDAO;
import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Account;
import by.trepam.domain.Answer;

public class PostgresqlAnswerDAO implements AnswerDAO{

	public void insert(Answer answer,int messageID)  throws DAOException {
		try(Connection connection = PostgresqlDAOFactory.createConnection()) {
			String sql = QueryConstants.INSERT_ANSWER;
			PreparedStatement stm;
			stm = connection.prepareStatement(sql);
			stm.setInt(1, answer.getId());
			stm.setInt(2, messageID);
			stm.setString(3, answer.getText());
			stm.setTimestamp(4, new Timestamp(answer.getDateOfPosting().getTime()));
			stm.setInt(5, answer.getAuthor().getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void delete(int answerID)  throws DAOException {
		try(Connection connection = PostgresqlDAOFactory.createConnection()) {
			String sql = QueryConstants.DELETE_ANSWER_BY_ID;
			PreparedStatement stm;
			stm = connection.prepareStatement(sql);
			stm.setInt(1, answerID);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
		
	}

	public Answer getAnswer(int answerID) throws DAOException {
		Connection connection = PostgresqlDAOFactory.createConnection();
		String sql = QueryConstants.GET_ANSWER_BY_ID;
		Answer answer = null;
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(sql);
			stm.setInt(1, answerID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				answer = new Answer();
				answer.setId(rs.getInt(1));
				answer.setText(rs.getString(2));
				answer.setDateOfPosting(rs.getTimestamp(3));
				answer.setAuthor(new Account(rs.getInt(4)));
			}
			rs.close();
			stm.close();
			connection.close();
			return answer;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public List<Answer> getAllAnswersOfMessage(int messageID)  throws DAOException {
		Connection connection = PostgresqlDAOFactory.createConnection();
		String sql = QueryConstants.GET_ALL_ANSWER_OF_MESSAGE;
		List<Answer> answers = new ArrayList<Answer>();
		Answer answer = null;
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(sql);
			stm.setInt(1, messageID);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				answer = new Answer();
				answer.setId(rs.getInt(1));
				answer.setText(rs.getString(2));
				answer.setDateOfPosting(rs.getTimestamp(3));
				answer.setAuthor(new Account(rs.getInt(4)));
				answers.add(answer);
			}
			rs.close();
			stm.close();
			connection.close();
			return answers;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void update(Answer answer,int messageID) throws DAOException {
		try {
			Connection connection = PostgresqlDAOFactory.createConnection();
			String sql = QueryConstants.UPDATE_ANSWER;
			PreparedStatement stm;
			stm = connection.prepareStatement(sql);
			stm.setInt(1, messageID);
			stm.setString(2, answer.getText());
			stm.setTimestamp(3, new Timestamp(answer.getDateOfPosting().getTime()));
			stm.setInt(4, answer.getAuthor().getId());
			stm.setInt(5, answer.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public double rating(int answerID) throws DAOException {
		Connection connection = PostgresqlDAOFactory.createConnection();
		String sql = QueryConstants.ANSWER_RATING_BY_ID;
		double rating = 0;
		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(sql);
			stm.setInt(1, answerID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				rating=rs.getDouble(1);
			}
			rs.close();
			stm.close();
			connection.close();
			return rating;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

}
