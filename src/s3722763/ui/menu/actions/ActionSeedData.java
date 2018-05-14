package s3722763.ui.menu.actions;

import s3722763.hireitems.Item;
import s3722763.hireitems.Movie;
import s3722763.util.DateTime;

public class ActionSeedData extends Action{

	public ActionSeedData() {
		super("Seed data");
	}

	@Override
	public ActionResult act(Item[] items) {
		tempRentalItems = new Item[10];
		//Weekly movies
		Movie movie1 = new Movie("M_MOV", "movie", "film", "description", false);
		tempRentalItems[0] = movie1;
		
		//New Releases
		Movie movie = new Movie("M_MOS", "Man of Steel", "Action", "description", true);
		movie.borrow("MEM");
		movie.returnItem(new DateTime(5));
		tempRentalItems[1] = movie;
		
		return ActionResult.SUCCESS;
	}
	
}
