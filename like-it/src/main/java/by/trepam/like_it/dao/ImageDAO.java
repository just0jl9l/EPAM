package by.trepam.like_it.dao;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Image;

/**
 * Interface class of data access layer. It provides access to images data.
 *
 */

public interface ImageDAO {

	/**
	 * Method inserts new image data in database.
	 * 
	 * @param image
	 * @throws DAOException
	 */
	void insert(Image image) throws DAOException;

	/**
	 * Method deletes image data, if image with imageId exists.
	 * 
	 * @param imageId
	 * @throws DAOException
	 */
	void delete(Integer imageId) throws DAOException;

	/**
	 * Method returns existing image or null, if image with imageId does not
	 * exist.
	 * 
	 * @param imageId
	 * @return
	 * @throws DAOException
	 */
	Image getImage(Integer imageId) throws DAOException;

	/**
	 * Method updates image data, if this image exists.
	 * 
	 * @param image
	 * @throws DAOException
	 */
	void update(Image image) throws DAOException;
}
