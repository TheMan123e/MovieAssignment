package s3722763.util.exceptions;

public class BorrowException extends Exception{
	public BorrowException(String name, String reason) {
		super("Cannot borrow " + name + " as item " + reason);
	}

}
