package s3722763.ui.menu;

import s3722763.ui.menu.items.MenuItem;

public class Menu {
	MenuItem[] menuItems;
	
	public Menu() {
		menuItems = new MenuItem[6];
		//1 - Add
		MenuItem ami = new MenuItem("Add Item", "A", 12);
		menuItems[0] = ami;
		//2 - Borrow
		//3 - Return
		//4 - Display details
		//5 - Seed Data
		//6 - Exit program
	}
	
	public void displayStart() {
		System.out.println("*** Movie Master System Menu ***");
		for(MenuItem mi : menuItems) {
			if (mi != null) {
				System.out.print(mi.getName());
				for (int i = 0; i < mi.getSpacing(); i++) {
					System.out.print(" ");
				}
				System.out.println(mi.getKey());
			}
		}
	}
}
