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
		System.out.println("Enter ID: ");
		String id = input.nextLine();
		Item toRent = null;
		
		//TODO: Accept Lower case or mix
		for(Item i : items) {
			if(i.getID().equals(id)) {
				toRent = i;
			}
		}
		
		if (toRent == null) {
			//TODO: Throw exception
		} else if (toRent.isCurrentlyBorrowed()) {
			//TODO: Throw exception
		}
		
		System.out.println("Enter Member ID: ");
		String memID = input.nextLine();
		System.out.println("Advance borrow (days): ");
		int advBorrow = input.nextInt();
	}

}
