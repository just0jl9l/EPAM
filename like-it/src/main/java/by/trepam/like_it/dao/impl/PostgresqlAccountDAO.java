package by.trepam.like_it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.connection_pool.exception.ConnectionPoolException;
import by.trepam.like_it.dao.connection_pool.impl.PostgresqlConnectionPool;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Image;

public class PostgresqlAccountDAO implements AccountDAO {

	public void insert(Account account) throws DAOException {
		String sql = QueryConstant.SQL_INSERT_ACCOUNT;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, account.getName());
			stm.setString(2, account.getSurname());
			stm.setString(3, account.getStatus());
			stm.setString(4, account.getLogin());
			stm.setString(5, account.getPassword());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}

	}

	public void delete(Integer accountId) throws DAOException {
		String sql = QueryConstant.SQL_DELETE_ACCOUNT_BY_ID;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, accountId);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}

	}

	public Account getAccount(Integer accountId) throws DAOException {
		String sql = QueryConstant.SQL_GET_ACCOUNT_BY_ID;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, accountId);
			ResultSet rs = stm.executeQuery();
			Account account = null;
			if (rs.next()) {
				account = new Account();
				account.setId(accountId);
				account.setLogin(rs.getString(1));
				account.setName(rs.getString(2));
				account.setSurname(rs.getString(3));
				account.setStatus(rs.getString(4));
				account.setPhoto(new Image(rs.getInt(5)));
			}
			return account;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}

	}

	public void update(Account account) throws DAOException {
		String sql = QueryConstant.SQL_UPDATE_ACCOUNT;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, account.getName());
			stm.setString(2, account.getSurname());
			stm.setInt(3, account.getPhoto().getId());
			stm.setInt(4, account.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public Account logIN(String login, String password) throws DAOException {
		String sql = QueryConstant.SQL_ACCOUNT_LOG_IN;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, login);
			stm.setString(2, password);
			ResultSet rs = stm.executeQuery();
			Account account = null;
			if (rs.next()) {
				account = new Account();
				account.setId(rs.getInt(1));
				account.setName(rs.getString(2));
				account.setSurname(rs.getString(3));
				account.setStatus(rs.getString(4));
				account.setPhoto(new Image(rs.getInt(5)));
				account.setLogin(login);
				account.setPassword(password);
			}
			return account;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public Double rating(Integer accountId) throws DAOException {
		String sql = QueryConstant.SQL_ACCOUNT_RATING_BY_ID;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, accountId);
			ResultSet rs = stm.executeQuery();
			Double rating = 0.0;
			if (rs.next()) {
				rating = rs.getDouble(1);
			}
			return rating;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public boolean isLoginFree(String login) throws DAOException {
		String sql = QueryConstant.SQL_FIND_LOGIN;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, login);
			ResultSet rs = stm.executeQuery();
			if (!rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}

	}

}
