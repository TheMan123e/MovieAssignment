package s3722763.ui;

import s3722763.hireitems.Item;
import s3722763.ui.menu.Menu;

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
			
			
		}
	}
	
	public void addItemToRentalList(Item item) {
		int emptyIndex = -1;
		
		for (int i = 0; i < rentalItems.length; i++) {
			if (rentalItems[i] == null) {
				emptyIndex = i; //Found first slot avaliable
				break;
			}
		}
		
		if (emptyIndex == -1) {
			Item[] newArray = new Item[rentalItems.length];
			
			for (int i = 0; i < rentalItems.length; i++) {
				newArray[i] = rentalItems[i];
			}
			
			emptyIndex = rentalItems.length;
			rentalItems = newArray;
		}
		
		rentalItems[emptyIndex] = item;
	}
}
