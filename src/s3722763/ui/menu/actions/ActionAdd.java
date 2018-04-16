package s3722763.ui.menu.actions;

import java.util.Scanner;

import s3722763.hireitems.Item;

public class ActionAdd extends Action {
	Item[] items;
	
	public void updateItems(Item[] items) {
		this.items = items;
	}
	
	@Override
	public ActionResult act() {
		Scanner in = new Scanner(System.in);
		ActionResult result;
		
		System.out.print("Enter ID: ");
		String id = in.nextLine();
		boolean alreadyAdded = checkID(id);
		
		if (alreadyAdded) {
			result =  ActionResult.FAILURE;
			reasonForFailure = "Item already exists";
			
			return result;
		}
		
		return null;
	}
	
	private boolean checkID(String id) {
		boolean result = false;
		
		for(Item i : items) {
			if (i.getID().equals(id)) {
				result = true;
			}
		}
		
		return result;
	}

}
