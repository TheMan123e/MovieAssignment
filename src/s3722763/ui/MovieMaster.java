package s3722763.ui;

import s3722763.hireitems.Item;
import s3722763.ui.menu.Menu;
import s3722763.ui.menu.actions.Action;
import s3722763.ui.menu.actions.ActionAdd;
import s3722763.ui.menu.actions.ActionResult;

public class MovieMaster {	
	Item[] rentalItems;
	Menu menu;
	/*
	 * Load menu
	 * Get user key
	 * find menu item with that key
	 * 
	 * 
	 */
	public MovieMaster() {
		rentalItems = new Item[2];
		menu = new Menu();
	}
	
	public void run() {
		boolean endProgram = false;
		
		while (!endProgram) {
			menu.displayStart();
			Action a = menu.getActionFromInput();
			ActionResult ar = a.act(rentalItems);
			if (ar == ActionResult.SUCCESS) {
				System.out.println("Successfully preformed action " + a.getName());
			} else {
				//Failed for some reason
				System.out.println("Failed to do action: " + a.getReasonForFailure());
			}
		}
	}
}
