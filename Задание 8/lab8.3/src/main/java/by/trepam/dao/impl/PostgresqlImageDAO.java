package by.trepam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.trepam.dao.ImageDAO;
import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Image;

public class PostgresqlImageDAO implements ImageDAO{

	public void insert(Image image)  throws DAOException {
		String sql = QueryConstants.INSERT_IMAGE;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, image.getId());
			stm.setString(2, image.getFormat());
			stm.setString(3, image.getPath());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void delete(int imageID)  throws DAOException {
		String sql = QueryConstants.DELETE_IMAGE_BY_ID;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
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
		try (Connection connection = PostgresqlDAOFactory.createConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, imageID);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				image = new Image();
				image.setId(rs.getInt(1));
				image.setPath(rs.getString(2));
				image.setFormat(rs.getString(3));
			}
			return image;
		} catch (SQLException e) {
			throw new DAOException("SQLException", e);
		}
	}

	public void update(Image image) throws DAOException {
		String sql = QueryConstants.UPDATE_IMAGE;
		try (Connection connection = PostgresqlDAOFactory.createConnection();
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
