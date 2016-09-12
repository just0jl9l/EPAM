package by.trepam.like_it.dao;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Image;

public interface ImageDAO {

	void insert(Image image) throws DAOException;
	void delete(Integer imageId) throws DAOException;
	Image getImage(Integer imageId) throws DAOException;
	void update(Image image) throws DAOException;
}
