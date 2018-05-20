 package s3722763.ui.menu.actions;

import java.util.Scanner;

import s3722763.hireitems.Game;
import s3722763.hireitems.Item;
import s3722763.util.exceptions.BorrowException;
import s3722763.util.exceptions.IdException;

public class ActionBorrow extends Action {
	public ActionBorrow() {
		super("Borrow");
	}
	
	@Override
	public ActionResult act(Item[] items) throws Exception{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter ID: ");
		String id = input.nextLine().toUpperCase();
		Item toRent = null;
		boolean isGame = false;
		
		//TODO: Accept Lower case or mix
		for(Item i : items) {
			if (i != null) {
				if(i.getID().equals("G_" + id)) {
					toRent = i;
					isGame = true;
				} else if (i.getID().equals("M_" + id)) {
					toRent = i;
					isGame = false;
				}
			}
		}
		
		if (toRent == null) {
			throw new IdException("doesn't exist");
		} else if (toRent.isCurrentlyBorrowed()) {
			throw new BorrowException(toRent.getTitle(), "already borrowed");
		}
		
		System.out.print("Enter Member ID: ");
		String memID = input.nextLine().toUpperCase();
		System.out.print("Advance borrow (days): ");
		
		boolean extended = false;
		int advBorrow = input.nextInt();
		
		if (isGame) {
			System.out.println("Do you want to rent as an extended hire? (Y or N)");
			boolean selected = false;
			while(!selected) {
				String in = input.nextLine().toLowerCase();
				if (in.equals("y")) {
					extended = true;
					selected = true;
				} else if (in.equals("n")) {
					extended = false;
					selected = true;
				} else {
					selected = false;
				}
			}
		}
		
		if (extended) {
			((Game)toRent).extend();
		}
		
		double fee = 0;
		
		if (advBorrow > 0) {
			fee = toRent.borrow(memID, advBorrow);
		} else {
			fee = toRent.borrow(memID);
		}
		
		String forFee = String.format("%1.2f", fee);
		
		System.out.println("The item " + toRent.getTitle() + " costs $" + forFee + " and is due on: " + toRent.getDateToReturn().getFormattedDate());
		
		return ActionResult.SUCCESS;
	}

}
