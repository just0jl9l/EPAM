package by.trepam.like_it.service.exception;

public class WrongDataException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public WrongDataException(String message){
		super(message);
	 }
	 
	 public WrongDataException(String message, Exception e){
		  super(message,e);
	}

}
