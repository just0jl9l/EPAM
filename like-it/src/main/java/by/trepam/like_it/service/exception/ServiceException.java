package by.trepam.like_it.service.exception;

/**
 * 
 * Throws when exception occurred in service layout.
 *
 */

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
