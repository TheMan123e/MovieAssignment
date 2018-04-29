package s3722763.ui.menu.actions;

import java.util.Scanner;

import s3722763.hireitems.Item;
import s3722763.hireitems.Movie;

//TODO: Add case for game and movie (Seperate them) as the following only covers movies
public class ActionAdd extends Action {
	public ActionAdd() {
		super("Add");
	}
	
	@Override
	public ActionResult act(Item[] items) {
		tempRentalItems = items;
		Item createdItem;
		
		if (items == null) {
			reasonForFailure = "The item array must be set before it is used";
			return ActionResult.FAILURE;
		}
		
		Scanner in = new Scanner(System.in);
		
		//TODO: Movie has M_ and game has G_
		System.out.print("Enter ID: ");
		String id = in.nextLine();
		boolean alreadyAdded = checkID(id, items);
		
		if (alreadyAdded) {
			reasonForFailure = "Item already exists";
			return ActionResult.FAILURE;
		} else if (id.length() != 3) {
			reasonForFailure = "ID needs to be 3 characters";
			return ActionResult.FAILURE;
		}
		
		//For movie
		id = "M_" + id;
		
		System.out.print("Enter title: ");
		String title = in.nextLine();
		
		System.out.print("Enter genre");
		String genre = in.nextLine();
		
		System.out.print("Enter description: ");
		String description = in.nextLine();
		
		boolean release = false;
		boolean isNewRelease = false;
		while (!release) {
			System.out.print("Enter new release (Y/N)");
			String newRelease = in.nextLine();
			
			if (newRelease.toLowerCase().equals("y")) {
				isNewRelease = true;
				release = true;
			} else if (newRelease.toLowerCase().equals("n")) {
				isNewRelease = false;
				release = true;
			} else {
				System.out.println("You need to enter either y or n not " + newRelease);
			}
		}
		
		createdItem = new Movie(id, title, genre, description, isNewRelease);
		addItemToRentalList(createdItem);
		
		return ActionResult.SUCCESS;
	}
	

	
	public void addItemToRentalList(Item item) {
		int emptyIndex = -1;
		
		for (int i = 0; i < tempRentalItems.length; i++) {
			if (tempRentalItems[i] == null) {
				emptyIndex = i; //Found first slot avaliable
				break;
			}
		}
		
		if (emptyIndex == -1) {
			Item[] newArray = new Item[tempRentalItems.length];
			
			for (int i = 0; i < tempRentalItems.length; i++) {
				newArray[i] = tempRentalItems[i];
			}
			
			emptyIndex = tempRentalItems.length;
			tempRentalItems = newArray;
		}
		
		tempRentalItems[emptyIndex] = item;
	}
	
	private boolean checkID(String id, Item[] items) {
		boolean result = false;
		
		for(Item i : items) {
			if (i != null) {
				if (i.getID().equals(id)) {
					result = true;
				}
			}
		}
		
		return result;
	}
}
