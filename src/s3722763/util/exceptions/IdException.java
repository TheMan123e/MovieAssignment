package s3722763.util.exceptions;

public class IdException extends Exception {
	public IdException(String exists) {
		super("ERROR: id " + exists);
	}
}
