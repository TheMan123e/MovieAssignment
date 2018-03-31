package s3722763.ui;

import java.util.Scanner;

import s3722763.hireitems.Movie;

public class ActionAddItem extends UIAction {
	public ActionAddItem() {
		super("Add Item", "A");
	}

	//TODO: Check the input
	@Override
	public void action() {
		//TODO: if this should come from movie master
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter id: ");
		String id = scanner.nextLine();
		
		System.out.print("Enter title: ");
		String title = scanner.nextLine();
		
		System.out.print("Enter genre: ");
		String genre = scanner.nextLine();
		
		System.out.print("Enter description: ");
		String description = scanner.nextLine();
		
		System.out.print("Enter new release (Y/N): ");
		String isNewRelease = scanner.nextLine();
		
		boolean newRelease = false;
		if (isNewRelease.toLowerCase().equals("y")) {
			newRelease = true;
		}
		
		Movie movie = new Movie(id, title, genre, description, newRelease);
		
		scanner.close();
	}

}
