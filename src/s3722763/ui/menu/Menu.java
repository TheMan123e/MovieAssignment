package s3722763.ui.menu;

import java.util.Scanner;

import s3722763.ui.menu.actions.Action;
import s3722763.ui.menu.actions.ActionAdd;
import s3722763.ui.menu.items.MenuItem;

public class Menu {
	MenuItem[] menuItems;
	
	public Menu() {
		menuItems = new MenuItem[6];
		//1 - Add
		ActionAdd aa = new ActionAdd();
		MenuItem ami = new MenuItem("Add Item", "A", 12, aa);
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
	
	public Action getActionFromInput() {
		Scanner input = new Scanner(System.in);
		String key = input.nextLine();
		Action a = null;
		
		for (MenuItem mi : menuItems) {
			if (mi != null) {
				if (mi.getKey().toLowerCase().equals(key.toLowerCase())) {
					a = mi.getAction();
				}
			}
		}
		
		return a;
	}
}
