package by.trepam.like_it.service.exception;

public class GettingDataException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public GettingDataException(String message){
		super(message);
	 }
	 
	 public GettingDataException(String message, Exception e){
		  super(message,e);
	}

}
