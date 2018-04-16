package s3722763.ui.menu.actions;

import java.util.Scanner;

import s3722763.hireitems.Item;
import s3722763.hireitems.Movie;

//TODO: Add case for game and movie (Seperate them) as the following only covers movies
public class ActionAdd extends Action {
	Item[] items;
	private Item createdItem;
	
	public void updateItems(Item[] items) {
		this.items = items;
		items = null;
	}
	
	@Override
	public ActionResult act() {
		if (items == null) {
			reasonForFailure = "The item array must be set before it is used";
			return ActionResult.FAILURE;
		}
		
		Scanner in = new Scanner(System.in);
		
		//TODO: Movie has M_ and game has G_
		System.out.print("Enter ID: ");
		String id = in.nextLine();
		boolean alreadyAdded = checkID(id);
		
		if (alreadyAdded) {
			reasonForFailure = "Item already exists";
			in.close();
			return ActionResult.FAILURE;
		} else if (id.length() != 3) {
			reasonForFailure = "ID needs to be 3 characters";
			in.close();
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
		
		return ActionResult.SUCCESS;
	}
	
	private boolean checkID(String id) {
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
	
	public Item getCreatedItem() {
		return createdItem;
	}
}
