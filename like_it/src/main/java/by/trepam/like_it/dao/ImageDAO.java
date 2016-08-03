package by.trepam.like_it.dao;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Image;

public interface ImageDAO {

	void insert(Image image) throws DAOException;
	void delete(Integer imageID) throws DAOException;
	Image getImage(Integer imageID) throws DAOException;
	void update(Image image) throws DAOException;
}
