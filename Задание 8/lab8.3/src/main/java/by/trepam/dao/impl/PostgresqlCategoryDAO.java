package by.trepam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.trepam.dao.CategoryDAO;
import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Category;
import by.trepam.domain.Image;

public class PostgresqlCategoryDAO implements CategoryDAO{

	public void insert(Category category) throws DAOException {
		String sql = QueryConstants.INSERT_CATEGORY;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, category.getId());
			stm.setString(2, category.getName());
			stm.setString(3, category.getDescription());
			stm.setInt(4, category.getImage().getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void delete(int categoryID) throws DAOException {
		String sql = QueryConstants.DELETE_CATEGORY_BY_ID;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, categoryID);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
		
	}

	public Category getCategory(int categoryID)  throws DAOException {
		String sql = QueryConstants.GET_CATEGORY_BY_ID;
		Category category = null;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, categoryID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setId(rs.getInt(1));
				category.setName(rs.getString(2));
				category.setDescription(rs.getString(3));
				category.setImage(new Image(rs.getInt(4)));
			}
			return category;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public List<Category> getAllCategories() throws DAOException {
		String sql = QueryConstants.GET_ALL_CATEGORIES;
		List<Category> categories = new ArrayList<Category>();
		Category category = null;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				category = new Category();
				category.setId(rs.getInt(1));
				category.setName(rs.getString(2));
				category.setDescription(rs.getString(3));
				category.setImage(new Image(rs.getInt(4)));
				categories.add(category);
			}
			return categories;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void update(Category category) throws DAOException {
		String sql = QueryConstants.UPDATE_CATEGORY;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, category.getName());
			stm.setString(2, category.getDescription());
			stm.setInt(3, category.getImage().getId());
			stm.setInt(4, category.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

}
