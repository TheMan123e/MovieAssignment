package s3722763.ui.menu.actions;

import java.util.Scanner;

import s3722763.hireitems.Game;
import s3722763.hireitems.Item;
import s3722763.hireitems.Movie;
import s3722763.util.exceptions.IdException;
/*
 * Class: ActionAdd
 * Description: This class represents an action which adds a rentable item
 * 				to the array of rentable items
 * Author: Daniel Miskimmin	- 3722763
 */
public class ActionAdd extends Action {
	public ActionAdd() {
		super("Add");
	}
	
	@Override
	public ActionResult act(Item[] items) throws IdException {
		tempRentalItems = items;
		Item createdItem = null;
		
		if (items == null) {
			reasonForFailure = "The item array must be set before it is used";
			return ActionResult.FAILURE;
		}
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter ID: ");
		String id = in.nextLine();
		boolean alreadyAdded = checkID(id, items);
		
		if (alreadyAdded) {
			throw new IdException("exists");
		} else if (id.length() != 3) {
			throw new IdException("needs 3 characters");
		}
		
		System.out.print("Enter title: ");
		String title = in.nextLine();
		
		System.out.print("Enter genre: ");
		String genre = in.nextLine();
		
		System.out.print("Enter description: ");
		String description = in.nextLine();
		
		boolean isMovie = false;
		boolean release = false;
		System.out.println("Are you adding a game or movie? (M or G)");
		while(!release) {
			String type = in.nextLine();
			if (type.toLowerCase().equals("m")) {
				isMovie = true;
				release = true;
			} else if (type.toLowerCase().equals("g")) {
				isMovie = false;
				release = true;
			} else {
				System.out.println("Must input either m or g");
			}
		}
		
		release = false;
		if (isMovie) {
			id = "M_" + id;
			boolean isNewRelease = false;
			while (!release) {
				System.out.print("Enter new release (Y/N): ");
				String newRelease = in.nextLine();
				
				if (newRelease.toLowerCase().equals("y")) {
					isNewRelease = true;
					release = true;
				} else if (newRelease.toLowerCase().equals("n")) {
					isNewRelease = false;
					release = true;
				} else {
					System.out.println("You need to enter either y "
							+ "or n not " + newRelease);
				}
			}
		
			createdItem = new Movie(id, title, genre, description, isNewRelease);
		} else {
			id = "G_" + id;
			System.out.println("Enter game platforms (Seperate with comma): ");
			String result = in.nextLine();
			String[] platforms = result.trim().split(",");
			
			createdItem = new Game(id, title, genre, description, platforms);
		}
		
		if (createdItem != null) {
			addItemToRentalList(createdItem);
		}
		
		return ActionResult.SUCCESS;
	}
	
	/*
	 * ALGORITHM
	 * BEGIN
	 * 		FOREACH item in rental item array
	 * 			IF item is null
	 * 				SET index to put item as index at
	 * 		
	 *		IF index equals -1
	 *			CREATE array one size bigger
	 *			SET index as last index in array
	 *		
	 *		SET item at chosen index as item passed through	
	 * END
	 */
	public void addItemToRentalList(Item item) {
		int emptyIndex = -1;
		
		for (int i = 0; i < tempRentalItems.length; i++) {
			if (tempRentalItems[i] == null) {
				emptyIndex = i; //Found first slot avaliable
				break;
			}
		}
		
		if (emptyIndex == -1) {
			Item[] newArray = new Item[tempRentalItems.length + 1];
			
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
