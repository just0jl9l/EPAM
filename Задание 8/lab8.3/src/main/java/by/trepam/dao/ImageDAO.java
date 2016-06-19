package by.trepam.dao;

import by.trepam.dao.exception.DAOException;
import by.trepam.domain.Image;

public interface ImageDAO {

	void insert(Image image) throws DAOException;
	void delete(int imageID) throws DAOException;
	Image getImage(int imageID) throws DAOException;
	void update(Image image) throws DAOException;
}
