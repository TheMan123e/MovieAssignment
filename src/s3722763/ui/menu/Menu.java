package s3722763.ui.menu;

import java.util.Scanner;

import s3722763.ui.menu.actions.Action;
import s3722763.ui.menu.actions.ActionAdd;
import s3722763.ui.menu.actions.ActionBorrow;
import s3722763.ui.menu.actions.ActionDisplay;
import s3722763.ui.menu.actions.ActionExit;
import s3722763.ui.menu.actions.ActionReturn;
import s3722763.ui.menu.actions.ActionSeedData;
import s3722763.ui.menu.items.MenuItem;

public class Menu {
	MenuItem[] menuItems;
	
	public Menu() {
		menuItems = new MenuItem[6];
		//1 - Add
		ActionAdd actionAdd = new ActionAdd();
		MenuItem addMenuItem = new MenuItem("Add Item", "A", 12, actionAdd);
		menuItems[0] = addMenuItem;
		//2 - Borrow
		ActionBorrow actionBorrow = new ActionBorrow();
		MenuItem  borrowMenuItem = new MenuItem("Borrow Item", "B", 9, actionBorrow);
		menuItems[1] = borrowMenuItem;
		//3 - Return
		ActionReturn actionReturn = new ActionReturn();
		MenuItem returnMenuItem = new MenuItem("Return Item", "C", 9, actionReturn);
		menuItems[2] = returnMenuItem;
		//4 - Display details
		ActionDisplay actionDisplay  = new ActionDisplay();
		MenuItem displayMenuItem = new MenuItem("Display details", "D", 5, actionDisplay);
		menuItems[3] = displayMenuItem;
		//5 - Seed Data
		ActionSeedData actionSeedData = new ActionSeedData();
		MenuItem seedDataMenuItem = new MenuItem("Seed data", "E", 11, actionSeedData);
		menuItems[4] = seedDataMenuItem;
		//6 - Exit program
		ActionExit actionExit = new ActionExit();
		MenuItem exitMenuItem = new MenuItem("Exit Program", "X", 8, actionExit);
		menuItems[5] = exitMenuItem;
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
		String key = null;
		System.out.print("Enter selection: ");
		
		key = input.nextLine();
		
		Action a = null;
		
		if (key != null) {
			for (MenuItem mi : menuItems) {
				if (mi != null) {
					if (mi.getKey().toLowerCase().equals(key.toLowerCase())) {
						a = mi.getAction();
					}
				}
			}
		}
		
		if (a == null && key != null) {
			System.out.println("Could not find menu action corrisponding to key " + key);
		}
		
		return a;
	}
}
