package by.trepam.like_it.dao.exception;

/**
 * Throws when exception occurred in data access layout.
 *
 */

public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

	public DAOException(String message, Exception e) {
		super(message, e);
	}
}
