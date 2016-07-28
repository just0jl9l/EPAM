package by.trepam.like_it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import by.trepam.like_it.dao.AnswerDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Answer;

public class PostgresqlAnswerDAO implements AnswerDAO{

	public void insert(Answer answer,int messageID)  throws DAOException {
		String sql = QueryConstants.INSERT_ANSWER;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, messageID);
			stm.setString(2, answer.getText());
			stm.setTimestamp(3, new Timestamp(answer.getDateOfPosting().getTime()));
			stm.setInt(4, answer.getAuthor().getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void delete(int answerID)  throws DAOException {
		String sql = QueryConstants.DELETE_ANSWER_BY_ID;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerID);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
		
	}

	public Answer getAnswer(int answerID) throws DAOException {
		String sql = QueryConstants.GET_ANSWER_BY_ID;
		Answer answer = null;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				answer = new Answer();
				answer.setText(rs.getString(1));
				answer.setDateOfPosting(rs.getTimestamp(2));
				answer.setAuthor(new Account(rs.getInt(3)));
				answer.setId(answerID);
			}
			return answer;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public List<Answer> getAllAnswersOfMessage(int messageID)  throws DAOException {
		String sql = QueryConstants.GET_ALL_ANSWER_OF_MESSAGE;
		List<Answer> answers = new ArrayList<Answer>();
		Answer answer = null;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
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
			return answers;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void update(Answer answer,int messageID) throws DAOException {
		String sql = QueryConstants.UPDATE_ANSWER;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
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
		String sql = QueryConstants.ANSWER_RATING_BY_ID;
		double rating = -1;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				rating=rs.getDouble(1);
			}
			return rating;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

}
