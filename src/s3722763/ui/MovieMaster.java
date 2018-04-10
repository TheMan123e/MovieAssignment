package s3722763.ui;

import java.util.Scanner;

import s3722763.hireitems.Item;
import s3722763.hireitems.Movie;
import s3722763.ui.result.IResult;
import s3722763.ui.result.ResultAdd;

public class MovieMaster {
	private UIAction[] actions;
	private Movie[] movies;
	
	Scanner input;
	
	public MovieMaster() {
		actions = new UIAction[6];
		input = new Scanner(System.in);
		movies = new Movie[1];
		
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
	}
	
	public void displayOptions() {
		System.out.print("Enter selection: ");
		String request = input.nextLine().toUpperCase();
		
		int indexOfAction = indexOfItem(request);
		IResult result = null;
		
		if (indexOfAction != -1) {
			result = actions[indexOfAction].action();
		}
		
		if (indexOfAction == -1 || result == null) {
			//Error getting action index or error processing action
		}
		
		if (result.typeOfResult().equals("add")) {
			ResultAdd ra = (ResultAdd)result;
			
			if (ra.item instanceof Movie) {
				Movie movie = (Movie) ra.item;
				System.out.println("Adding a movie to the list of avaliable movies");
			}
		}
		
	}
	
	private void addItem(Item item) {
		if (item instanceof Movie) {
			Movie m = (Movie)item;
			
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
