package s3722763.ui.menu.actions;

import java.util.Scanner;

import s3722763.hireitems.Item;
import s3722763.util.DateTime;
import s3722763.util.exceptions.BorrowException;
/*
 * Class: ActionReturn
 * Description: This class implements an action which does the opposite
 * 				to borrow, by allowing the user to return the rented item
 * Author: Daniel Miskimmin	- 3722763
 */
public class ActionReturn extends Action {
	public ActionReturn() {
		super("Return");
	}
	
	/*
	 * ALGORITHM
	 * BEGIN
	 * 		GET id of item to rent
	 * 		IF item id doesn't exist in rental item array
	 * 			THROW BorrowException that the id doesn't exist
	 * 		IF item is not borrowed
	 * 			THROW BorrowException that item is not currently borrowed
	 * 		GET number of days on load
	 * 		IF days less than zero
	 * 			THROW Error that days has to be a positive number
	 * 		RETURN borrow
	 * 		DISPLAY fee needed to be paid
	 * END
	 */
	@Override
	public ActionResult act(Item[] items) throws BorrowException{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter ID: ");
		String id = "M_" + in.nextLine().toUpperCase();
		Item toReturn = null;
		
		for (Item i : items) {
			if (i != null) {
				if (i.getID().equals(id)) {
					toReturn = i;
				}
			}
		}
		
		if (toReturn == null) {
			throw new BorrowException(id, "doesn't exist");
		} else if (!toReturn.isCurrentlyBorrowed()) {
			throw new BorrowException(id, "not borrowed");
		}
		
		System.out.print("Enter number of days on loan: ");
		int days = in.nextInt();
		
		if (days < 0) {
			System.out.println("Days has to be a positive number");
			return ActionResult.FAILURE;
		} 
		
		double fee = toReturn.returnItem(new DateTime(days));
		String forFee = String.format("%1.2f", fee);
		
		System.out.println("The total fee payable is $" + forFee);
		return ActionResult.SUCCESS;
	}
	
}
