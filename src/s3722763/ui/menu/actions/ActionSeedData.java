package s3722763.ui.menu.actions;

import s3722763.hireitems.Game;
import s3722763.hireitems.Item;
import s3722763.hireitems.Movie;
import s3722763.util.DateTime;

public class ActionSeedData extends Action{

	public ActionSeedData() {
		super("Seed data");
	}

	@Override
	public ActionResult act(Item[] items) throws Exception {
		tempRentalItems = new Item[14];
		//Weekly movies
		Movie movie = new Movie("M_MOV", "movie", "Documentry", "description", false);
		tempRentalItems[0] = movie;
		
		Movie movie1 = new Movie("M_DEA", "Deadpool", "Äction", "description", false);
		movie1.borrow("MEM");
		tempRentalItems[1] = movie1;
		
		Movie movie2 = new Movie("M_RES", "Restrepo", "Documentry", "description", false);
		movie2.borrow("MEM");
		movie2.returnItem(new DateTime(5));
		tempRentalItems[2] = movie2;
		
		Movie movie3 = new Movie("M_TMA", "Team America: World Police", "Action", "description", false);
		movie3.borrow("MEM");
		movie3.returnItem(new DateTime(10));
		tempRentalItems[3] = movie3;
		
		Movie movie4 = new Movie("M_SCE", "Sand Castle", "Action", "description", false);
		movie4.borrow("MEM");
		movie4.returnItem(new DateTime(10));
		//10 days ahead
		movie4.borrow("MEa", 10);
		tempRentalItems[4] = movie4;
		
		//New Releases
		Movie movie5 = new Movie("M_MOS", "Man of Steel", "Action", "description", true);
		movie5.borrow("MEM");
		movie5.returnItem(new DateTime(5));
		tempRentalItems[5] = movie5;
		
		Movie movie6 = new Movie("M_DRS", "Doctor Strange", "Action", "description", true);
		movie6.borrow("MEM");
		tempRentalItems[6] = movie6;
		
		Movie movie7 = new Movie("M_BOC", "The Battle of Chosin", "Documentry", "description", true);
		movie7.borrow("MEM");
		movie7.returnItem(new DateTime(1));
		tempRentalItems[7] = movie7;
		
		Movie movie8 = new Movie("M_JW2", "John Wick: Chapter 2", "Action", "description", true);
		movie8.borrow("MEM");
		movie8.returnItem(new DateTime(3));
		tempRentalItems[8] = movie8;
		
		Movie movie9 = new Movie("M_DFL", "Downfall", "Drama", "description", true);
		movie9.borrow("MEM");
		movie9.returnItem(new DateTime(3));
		movie9.borrow("MEA", 3);
		tempRentalItems[9] = movie9;
		
		//Games
		String[] platforms1 = {"PC"};
		Game game1 = new Game("G_GW1", "Guild Wars", "Action", "description", platforms1);
		tempRentalItems[10] = game1;
		
		String[] platforms2 = {"PC", "XBox 1", "PS4"};
		Game game2 = new Game("G_BF1", "Battlefield 1", "Action", "description", platforms2);
		game2.borrow("MEM");
		tempRentalItems[11] = game2;
		
		String[] platforms3 = {"PC"};
		Game game3 = new Game("G_XP1", "XPlane 11", "Flight Simulator", "description", platforms3);
		game3.borrow("MEM");
		game3.returnItem(new DateTime(19));
		tempRentalItems[12] = game3;
		
		String[] platforms4 = {"PC"};
		Game game4 = new Game("G_TIS", "TIS-100", "Action", "Puzzle", platforms4);
		game4.borrow("MEM");
		game4.returnItem(new DateTime(32));
		tempRentalItems[13] = game4;
		
		return ActionResult.SUCCESS;
	}


	/*
	@Override
	public ActionResult act(Item[] items) throws Exception {
		tempRentalItems = new Item[1];
		
		Movie movie = new Movie("M_RES", "Restrepo", "Documentry", "description", false);
		movie.borrow("MEM");
		movie.returnItem(new DateTime(5));
		tempRentalItems[1] = movie;
		
		return ActionResult.SUCCESS;
	}*/
	
}
