package by.trepam.like_it.dao.connection_pool.exception;

public class ConnectionPoolException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ConnectionPoolException(String message, Exception e){
		super(message,e);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}
}
