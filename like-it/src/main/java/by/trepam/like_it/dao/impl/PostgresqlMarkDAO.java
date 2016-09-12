package by.trepam.like_it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import by.trepam.like_it.dao.MarkDAO;
import by.trepam.like_it.dao.connection_pool.exception.ConnectionPoolException;
import by.trepam.like_it.dao.connection_pool.impl.PostgresqlConnectionPool;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Mark;

public class PostgresqlMarkDAO implements MarkDAO{

	public void insert(Mark mark,Integer answerId)  throws DAOException {
		String sql = QueryConstant.SQL_INSERT_MARK;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerId);
			stm.setInt(2, mark.getAuthor().getId());
			stm.setInt(3, mark.getValue());
			stm.setTimestamp(4, new Timestamp(mark.getDateOfVoting().getTime()));
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public void delete(Integer authorId,Integer answerId)  throws DAOException {
		String sql = QueryConstant.SQL_DELETE_MARK;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerId);
			stm.setInt(2, authorId);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
		
	}

	public List<Mark> getAllMarksOfAnswer(Integer answerId)  throws DAOException {
		String sql = QueryConstant.SQL_GET_ALL_MARKS_OF_ANSWER;
		List<Mark> marks = new ArrayList<Mark>();
		Mark mark = null;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, answerId);
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
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public void update(Mark mark,Integer answerId) throws DAOException {
		String sql = QueryConstant.SQL_UPDATE_MARK;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, mark.getValue());
			stm.setTimestamp(2, new Timestamp(mark.getDateOfVoting().getTime()));
			stm.setInt(3, answerId);
			stm.setInt(4, mark.getAuthor().getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

}
