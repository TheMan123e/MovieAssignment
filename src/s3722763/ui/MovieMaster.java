package s3722763.ui;

import java.util.Scanner;

public class MovieMaster {
	private UIAction[] actions;
	Scanner input;
	
	public MovieMaster() {
		actions = new UIAction[6];
		input = new Scanner(System.in);
		
		ActionAddItem aai = new ActionAddItem();
		actions[0] = aai;
		
		ActionBorrowItem abi = new ActionBorrowItem();
		actions[1] = abi;
		
		ActionReturnItem ari = new ActionReturnItem();
		actions[2] = ari;
	}
	
	public void loadMenu() {
		System.out.println("*** Movie Master System Menu ***");
		for (UIAction action : actions) {
			if (action != null) {
				displayMenuItem(action);
			}
		}
		
		System.out.print("Enter selection: ");
		String request = input.nextLine().toUpperCase();
		
		int indexOfAction = indexOfItem(request);
		
		if (indexOfAction != -1) {
			actions[indexOfAction].action();
		}
	}
	
	private int indexOfItem(String key) {
		for (int i = 0; i < actions.length; i++) {
			if (actions[i] != null) {
				if (actions[i].getKey().equals(key)) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	private void displayMenuItem(UIAction action) {
		int numberOfSpaces = 27 - action.getName().length();
		
		String toDisplay = String.format("%s%" + numberOfSpaces + "s%s", action.getName(), " ", action.getKey());
		System.out.println(toDisplay);
	}
}
