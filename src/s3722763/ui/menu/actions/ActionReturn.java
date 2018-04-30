package s3722763.ui.menu.actions;

import java.util.Scanner;

import s3722763.hireitems.Item;
import s3722763.util.DateTime;

public class ActionReturn extends Action {
	public ActionReturn() {
		super("Return");
	}

	@Override
	public ActionResult act(Item[] items) {
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
			System.out.println("Item with id " + id + " does not exist");
			return ActionResult.FAILURE;
		} else if (!toReturn.isCurrentlyBorrowed()) {
			System.out.println("Item" + id + " is not currently borrowed");
			return ActionResult.FAILURE;
		}
		
		System.out.print("Enter number of days on loan: ");
		int days = in.nextInt();
		
		if (days < 0) {
			System.out.println("Days has to be a positive number");
			return ActionResult.FAILURE;
		} 
		
		//TODO: Change so it uses the day borrowed not todays date
		
		double fee = toReturn.returnItem(new DateTime(days));
		String forFee = String.format("%1.2f", fee);
		
		System.out.println("The total fee payable is $" + forFee);
		return ActionResult.SUCCESS;
	}
	
}
