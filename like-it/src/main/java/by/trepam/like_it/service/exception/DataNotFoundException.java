package by.trepam.like_it.service.exception;

/**
 * Throws in service layout, when requested data is not found.
 *
 */

public class DataNotFoundException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
