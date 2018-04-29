package s3722763.ui.menu;

import java.util.Scanner;

import s3722763.ui.menu.actions.Action;
import s3722763.ui.menu.actions.ActionAdd;
import s3722763.ui.menu.actions.ActionBorrow;
import s3722763.ui.menu.actions.ActionDisplay;
import s3722763.ui.menu.actions.ActionSeedData;
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
		ActionBorrow ab = new ActionBorrow();
		MenuItem bmi = new MenuItem("Borrow Item", "B", 12, ab);
		menuItems[1] = bmi;
		//3 - Return
		//4 - Display details
		ActionDisplay ad  = new ActionDisplay();
		MenuItem dmi = new MenuItem("Display details", "D", 0, ad);
		menuItems[3] = dmi;
		//5 - Seed Data
		ActionSeedData asd = new ActionSeedData();
		MenuItem emi = new MenuItem("Seed data", "E", 0, asd);
		menuItems[4] = emi;
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
		String key = null;
		
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
