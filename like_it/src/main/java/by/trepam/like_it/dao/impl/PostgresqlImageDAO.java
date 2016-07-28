package by.trepam.like_it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Image;

public class PostgresqlImageDAO implements ImageDAO{

	public void insert(Image image)  throws DAOException {
		String sql = QueryConstants.INSERT_IMAGE;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, image.getFormat());
			stm.setString(2, image.getPath());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void delete(int imageID)  throws DAOException {
		String sql = QueryConstants.DELETE_IMAGE_BY_ID;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, imageID);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
		
	}

	public Image getImage(int imageID) throws DAOException {
		String sql = QueryConstants.GET_IMAGE_BY_ID;
		Image image = null;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, imageID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				image = new Image();
				image.setId(imageID);
				image.setPath(rs.getString(1));
				image.setFormat(rs.getString(2));
			}
			return image;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void update(Image image) throws DAOException {
		String sql = QueryConstants.UPDATE_IMAGE;
		try (Connection connection = PostgresqlDAOFactory.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, image.getPath());
			stm.setString(2, image.getFormat());
			stm.setInt(3, image.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

}
