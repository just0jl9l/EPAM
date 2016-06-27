package by.trepam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import by.trepam.dao.MarkDAO;
import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Account;
import by.trepam.domain.Mark;

public class PostgresqlMarkDAO implements MarkDAO{

	public void insert(Mark mark,int answerID)  throws DAOException {
		String sql = QueryConstants.INSERT_MARK;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerID);
			stm.setInt(2, mark.getAuthor().getId());
			stm.setInt(3, mark.getValue());
			stm.setTimestamp(4, new Timestamp(mark.getDateOfVoting().getTime()));
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void delete(int authorID,int answerID)  throws DAOException {
		String sql = QueryConstants.DELETE_MARK;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, authorID);
			stm.setInt(2, answerID);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
		
	}

	public List<Mark> getAllMarksOfAnswer(int answerID)  throws DAOException {
		String sql = QueryConstants.GET_ALL_MARKS_OF_ANSWER;
		List<Mark> marks = new ArrayList<Mark>();
		Mark mark = null;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerID);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				mark = new Mark();
				mark.setAuthor(new Account(rs.getInt(1)));
				mark.setValue(rs.getInt(2));
				mark.setDateOfVoting(rs.getTimestamp(3));
				marks.add(mark);
			}
			return marks;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void update(Mark mark,int answerID) throws DAOException {
		String sql = QueryConstants.UPDATE_MARK;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, mark.getValue());
			stm.setTimestamp(2, new Timestamp(mark.getDateOfVoting().getTime()));
			stm.setInt(3, answerID);
			stm.setInt(4, mark.getAuthor().getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

}
