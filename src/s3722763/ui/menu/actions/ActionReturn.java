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
		String id = in.nextLine().toUpperCase();
		Item toReturn = null;
		
		for (Item i : items) {
			if (i.getID().equals(id)) {
				toReturn = i;
			}
		}
		
		if (toReturn == null) {
			System.out.println("Item with id " + id + " does not exist");
			return ActionResult.FAILURE;
		}
		
		System.out.print("Enter number of days on loan: ");
		int days = in.nextInt();
		
		if (days < 0) {
			System.out.println("Days has to be a positive number");
			return ActionResult.FAILURE;
		} 
		
		double fee = toReturn.returnItem(new DateTime(days));
		System.out.println("The total fee payable is $" + fee);
		return ActionResult.SUCCESS;
	}
	
}
