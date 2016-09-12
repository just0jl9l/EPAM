package by.trepam.like_it.service.exception;

public class DataNotFoundException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message){
		super(message);
	 }
	 
	 public DataNotFoundException(String message, Exception e){
		  super(message,e);
	}

}
