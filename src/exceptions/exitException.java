package exceptions;

public class exitException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public exitException(String msg) {
		super(msg);
	}

}
