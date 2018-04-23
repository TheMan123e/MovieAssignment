package s3722763.ui.menu.actions;

import s3722763.hireitems.Item;
import s3722763.hireitems.Movie;

public class ActionSeedData extends Action{

	public ActionSeedData() {
		super("Seed data");
	}

	@Override
	public ActionResult act(Item[] items) {
		tempRentalItems = new Item[1];
		
		Movie movie = new Movie("MOS", "Man of Steel", "Action", "description", true);
		tempRentalItems[0] = movie;
		
		return ActionResult.SUCCESS;
	}
	
}
