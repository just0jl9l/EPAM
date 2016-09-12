package by.trepam.like_it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.trepam.like_it.dao.CategoryDAO;
import by.trepam.like_it.dao.connection_pool.exception.ConnectionPoolException;
import by.trepam.like_it.dao.connection_pool.impl.PostgresqlConnectionPool;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.domain.Image;

public class PostgresqlCategoryDAO implements CategoryDAO{

	public void insert(Category category) throws DAOException {
		String sql = QueryConstant.SQL_INSERT_CATEGORY;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, category.getName());
			stm.setString(2, category.getDescription());
			stm.setInt(3, category.getImage().getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public void delete(Integer categoryId) throws DAOException {
		String sql = QueryConstant.SQL_DELETE_CATEGORY_BY_ID;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, categoryId);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
		
	}

	public Category getCategory(Integer categoryId,String lang)  throws DAOException {
		String sql = QueryConstant.SQL_GET_CATEGORY_BY_ID;
		Category category = null;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, lang);
			stm.setInt(2, categoryId);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setId(categoryId);
				category.setImage(new Image(rs.getInt(1)));
				category.setName(rs.getString(2));
				category.setDescription(rs.getString(3));
			}
			return category;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public List<Category> getAllCategories(String lang) throws DAOException {
		String sql = QueryConstant.SQL_GET_ALL_CATEGORIES;
		List<Category> categories = new ArrayList<Category>();
		Category category = null;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, lang);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				category = new Category();
				category.setId(rs.getInt(1));
				category.setImage(new Image(rs.getInt(2)));
				category.setName(rs.getString(3));
				category.setDescription(rs.getString(4));
				categories.add(category);
			}
			return categories;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public void update(Category category) throws DAOException {
		String sql = QueryConstant.SQL_UPDATE_CATEGORY;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, category.getName());
			stm.setString(2, category.getDescription());
			stm.setInt(3, category.getImage().getId());
			stm.setInt(4, category.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

	public Integer getCategoryId(String name) throws DAOException {
		String sql = QueryConstant.SQL_GET_CATEGORY_ID_BY_NAME;
		Integer id=-10;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, name);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
		return id;
	}

	public void insertText(Category category, String lang) throws DAOException {
		String sql = QueryConstant.SQL_INSERT_CATEGORY_TEXT;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, lang);
			stm.setString(2, category.getName());
			stm.setString(3, category.getDescription());
			stm.setInt(4, category.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}	 catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}	
	}

	public void updateText(Category category, String lang) throws DAOException {
		String sql = QueryConstant.SQL_UPDATE_CATEGORY_TEXT;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, category.getName());
			stm.setString(2, category.getDescription());
			stm.setInt(3, category.getId());
			stm.setString(4, lang);
			System.out.println(stm.toString());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
		
	}

	public void deleteText(Integer categoryId, String lang) throws DAOException {
		String sql = QueryConstant.SQL_DELETE_CATEGORY_TEXT;
		try (Connection connection = PostgresqlConnectionPool.getInstance().getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, categoryId);
			stm.setString(2, lang);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		} catch (ConnectionPoolException e1) {
			throw new DAOException("ConnectionPoolException", e1);
		}
	}

}
