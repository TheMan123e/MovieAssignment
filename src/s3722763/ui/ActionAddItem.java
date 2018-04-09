package s3722763.ui;

import java.util.Scanner;

import s3722763.hireitems.Movie;
import s3722763.ui.result.ResultAdd;

public class ActionAddItem extends UIAction {
	public ActionAddItem() {
		super("Add Item", "A");
	}

	//TODO: Check the input
	@Override
	public ResultAdd action() {
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
		
		//TODO: Checkif id input valid or title doesn't exist
		Movie movie = new Movie(id, title, genre, description, newRelease);
		ResultAdd ra = new ResultAdd(movie);
		
		scanner.close();
		
		return ra;
	}

}
