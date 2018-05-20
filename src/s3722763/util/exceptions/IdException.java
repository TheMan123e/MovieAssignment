package s3722763.util.exceptions;
/*
 * Class: IdException
 * Description: This class represents any exception which is caused by an
 * 				error to do with whether an id exists or not
 * Author: Daniel Miskimmin	- 3722763
 */
public class IdException extends Exception {
	public IdException(String exists) {
		super("ERROR: id " + exists);
	}
}
