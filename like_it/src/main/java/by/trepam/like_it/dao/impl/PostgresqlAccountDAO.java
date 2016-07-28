package by.trepam.like_it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Image;

public class PostgresqlAccountDAO implements AccountDAO {

	public void insert(Account account) throws DAOException {
		String sql = QueryConstants.INSERT_ACCOUNT;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, account.getName());
			stm.setString(2, account.getSurname());
			stm.setString(3, account.getStatus());
			stm.setString(4, account.getLogin());
			stm.setString(5, account.getPassword());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}

	}

	public void delete(int accountID) throws DAOException {
		String sql = QueryConstants.DELETE_ACCOUNT_BY_ID;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, accountID);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}

	}

	public Account getAccount(int accountID) throws DAOException {
		String sql = QueryConstants.GET_ACCOUNT_BY_ID;
		Account account = null;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, accountID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setId(accountID);
				account.setLogin(rs.getString(1));
				account.setName(rs.getString(2));
				account.setSurname(rs.getString(3));
				account.setStatus(rs.getString(4));
				account.setPhoto(new Image(rs.getInt(5)));
			}
			return account;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}

	}

	public void update(Account account) throws DAOException {
		String sql = QueryConstants.UPDATE_ACCOUNT;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, account.getName());
			stm.setString(2, account.getSurname());
			stm.setInt(3, account.getPhoto().getId());
			stm.setInt(4, account.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public Account logIN(String login, String password) throws DAOException {
		String sql = QueryConstants.ACCOUNT_LOG_IN;
		Account account = null;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, login);
			stm.setString(2, password);
			ResultSet rs = stm.executeQuery();
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
		}
	}

	public double rating(int accountID) throws DAOException {
		String sql = QueryConstants.ACCOUNT_RATING_BY_ID;
		double rating = 0;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, accountID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				rating = rs.getDouble(1);
			}
			return rating;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public boolean isLoginFree(String login) throws DAOException {
		String sql = QueryConstants.FIND_LOGIN;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, login);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
		
	}

}
