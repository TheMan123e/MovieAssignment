package s3722763.util.exceptions;
/*
 * Class: HiringRecord
 * Description: This class represents an exception which is caused
 * 				by an error to do with borrowing or returning an item
 * Author: Daniel Miskimmin	- 3722763
 */
public class BorrowException extends Exception{
	public BorrowException(String name, String reason) {
		super("Cannot borrow " + name + " as item " + reason);
	}

}
