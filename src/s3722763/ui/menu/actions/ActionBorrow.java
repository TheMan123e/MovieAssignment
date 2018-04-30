 package s3722763.ui.menu.actions;

import java.util.Scanner;

import s3722763.hireitems.Item;

public class ActionBorrow extends Action {
	public ActionBorrow() {
		super("Borrow");
	}
	
	@Override
	public ActionResult act(Item[] items) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter ID: ");
		String id = "M_" + input.nextLine().toUpperCase();
		Item toRent = null;
		
		//TODO: Accept Lower case or mix
		for(Item i : items) {
			if (i != null) {
				if(i.getID().equals(id)) {
					toRent = i;
				}
			}
		}
		
		if (toRent == null) {
			//TODO: Throw exception
			System.out.println("Item " + id + " does not exist");
			return ActionResult.FAILURE;
		} else if (toRent.isCurrentlyBorrowed()) {
			System.out.println("Item " + id + " is already borrowed");
			return ActionResult.FAILURE;
			//TODO: Throw exception
		}
		
		System.out.print("Enter Member ID: ");
		String memID = input.nextLine().toUpperCase();
		System.out.print("Advance borrow (days): ");
		int advBorrow = input.nextInt();
		//TODO: Need to know what advBorrow does
		//Will rent x nuber of days from now
		double fee = toRent.borrow(memID);
		String forFee = String.format("%1.2f", fee);
		
		System.out.println("The item " + toRent.getTitle() + " costs $" + forFee + " and is due on: " + toRent.getDateToReturn().getFormattedDate());
		
		return ActionResult.SUCCESS;
	}

}
