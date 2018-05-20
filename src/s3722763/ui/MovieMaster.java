package s3722763.ui;

import java.io.IOException;

import s3722763.hireitems.Game;
import s3722763.hireitems.HiringRecord;
import s3722763.hireitems.Item;
import s3722763.hireitems.Movie;
import s3722763.ui.menu.Menu;
import s3722763.ui.menu.actions.Action;
import s3722763.ui.menu.actions.ActionResult;
import s3722763.util.FileHandler;

/*
 * Class: MovieMaster
 * Description: This class represents the loading, saving and handling of actions
 * 				as well as handling the modifications to the rentalItems
 * Author: Daniel Miskimmin	- 3722763
 */
public class MovieMaster {	
	private final String DELIMITER = ":/";
	private Item[] rentalItems;
	private Menu menu;
	
	public MovieMaster() {
		rentalItems = new Item[2];
		menu = new Menu();
	}
	
	public void run() {
		boolean endProgram = false;
		
		try {
			loadRentalItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while (!endProgram) {
			menu.displayStart();
			Action a = menu.getActionFromInput();
			if (a != null) {
				ActionResult ar = null;
				
				try {
					ar = a.act(rentalItems);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
				if (ar == ActionResult.SUCCESS) {
					System.out.println("Successfully preformed action " + a.getName());
					if (a.getUpdatedList() != null) {
						rentalItems = a.getUpdatedList();
					}
				} else if(ar == ActionResult.END_PROGRAM) {
					try {
						saveRentalItems();
						endProgram = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (ar != null){
					//Failed for some reason but didn't have an exception
					System.out.println("Failed to do action: " + a.getReasonForFailure());
				}
			}
		}
	}
	
	/*
	 * ALGORITHM
	 * BEGIN
	 * 		LOAD file movies
	 * 		GET file delimiter
	 * 		CREATE list of movies from data loaded from file movies
	 * 		FOREACH item in created movies list
	 * 			LOAD file with item id name
	 * 			LOAD file with item hiring record
	 * 			ADD to rental item list
	 * 		LOAD file games
	 * 		Create list of game from data loaded from file game
	 * 		FOREACH item in created games list
	 * 			LOAD file with item id name
	 * 			LOAD file with item hiring record
	 * 			ADD to rental item list
	 * END
	 */
	private void loadRentalItems() throws IOException {
		FileHandler fileHandler = new FileHandler();
		//Movies
		String movies = fileHandler.load("movies");
		String fileDelimiter = fileHandler.getDelimiter();
		String[] moviesList = movies.split(fileDelimiter);
		loadMovies(fileHandler, moviesList);
		
		String games = fileHandler.load("games");
		String[] gamesList = games.split(fileDelimiter);
		loadGames(fileHandler, gamesList);
	}
	
	private void loadGames(FileHandler fileHandler, String[] gamesList) throws IOException {
		for (String id : gamesList) {
			if (!id.equals("") && !id.equals("null")) {
				//String id, String title, String genre, String description, String[] platforms
				String info = fileHandler.load(id);
				String[] infoSorted = info.split(":");
				boolean extended = infoSorted[6].toLowerCase().equals("e");
				String[] platforms = infoSorted[5].split(", ");
				Game game = new Game(infoSorted[0], infoSorted[1],
						infoSorted[3], infoSorted[2], platforms);
				if (extended) {
					game.extend();
				}
				
				HiringRecord[] rentalData = getRentalData(fileHandler, game.getID());
				game.setHiringRecord(rentalData);
				
				for(int i = 0; i < rentalData.length; i++) {
					HiringRecord hr = rentalData[i];
					if (hr != null && hr.getDateReturned() == null) {
						game.setCurrentlyBorrowed(i);
					}
				}
				
				addItemToRentalList(game);
			}
		}
	}
	
	private HiringRecord[] getRentalData(FileHandler fileHandler, String id) throws IOException {
		HiringRecord[] result = new HiringRecord[10];
		int index = 0;
		
		String rentalData = fileHandler.load(id + "_hiring_info");
		if (!rentalData.equals("")) {
			String[] rental = rentalData.split(DELIMITER);
			for(String s : rental) {
				//String id, String memberID, double rentalFee) 
				String[] elements = s.split(":");
				if (!elements[0].equals("null")) {
					HiringRecord hr = null;
					
					if (!elements[1].equals("none")) {
						//Means that the item has returned
						hr = new HiringRecord(elements[0], Integer.valueOf(elements[1]),
								Double.valueOf(elements[2]), Double.valueOf(elements[3]));
					} else {
						hr = new HiringRecord(elements[0]);
					}
					
					if (hr != null) {
						result[index] = hr;
						index++;
					}
				}
			}
		}
		
		return result;
	}
	
	private void loadMovies(FileHandler fileHandler, String[] moviesList) throws IOException {
		for(String id : moviesList) {
			if (!id.equals("") && !id.equals("null")) {
				String info = fileHandler.load(id);
				String[] infoSorted = info.split(":");
				
				boolean isNewRelease = true;
				if(infoSorted[5].equals("WL")) {
					isNewRelease = false;
				}
				
				Movie movie = new Movie(infoSorted[0], infoSorted[1],
						infoSorted[3], infoSorted[2], isNewRelease);
				HiringRecord[] hiringRecord = getRentalData(fileHandler, movie.getID());
				
				movie.setHiringRecord(hiringRecord);
				
				for(int i = 0; i < hiringRecord.length; i++) {
					HiringRecord hr = hiringRecord[i];
					if (hr != null && hr.getDateReturned() == null) {
						movie.setCurrentlyBorrowed(i);
					}
				}
				
				addItemToRentalList(movie);
			}
		}
	}
	
	private void saveRentalItems() throws IOException {
		FileHandler fileHandler = new FileHandler();
		
		String[] movieIDs = new String[1];
		String[] gameIDs = new String[1];
		
		for(Item item : rentalItems) {	
			if (item instanceof Game) {
				gameIDs = addItemToStringArray(item.getID(), gameIDs);
			} else if (item instanceof Movie) {
				movieIDs = addItemToStringArray(item.getID(), movieIDs);
			}
			
			String itemInfo = item.toString();
			HiringRecord[] hireInfo = item.getHireHistory();
				
			String hiringInfo = "";
				
			for(HiringRecord hr : hireInfo) {
				if (hr != null) {
					hiringInfo += hr.toString() + DELIMITER;
				}
			}
				
			fileHandler.save(itemInfo, item.getID());
			fileHandler.save(hiringInfo, item.getID() + "_hiring_info");
		}
		
		
		String moviesList = "";
		for(String s : movieIDs) {
			moviesList += s + fileHandler.getDelimiter();
		}
		
		String gamesList = "";
		for(String s : gameIDs) {
			gamesList += s + fileHandler.getDelimiter();
		}
		
		fileHandler.save(moviesList, "movies");
		fileHandler.save(gamesList, "games");
	}
	
	private String[] addItemToStringArray(String toAdd, String[] original) {
		String[] result;
		
		int emptyIndex = -1;
		
		for (int i = 0; i < original.length; i++) {
			if (original[i] == null) {
				emptyIndex = i; //Found first slot avaliable
				break;
			}
		}
		
		if (emptyIndex == -1) {
			result = new String[original.length + 1];
			
			for (int i = 0; i < original.length; i++) {
				result[i] = original[i];
			}
			
			emptyIndex = result.length - 1;
		} else {
			result = original;
		}
		
		result[emptyIndex] = toAdd;
		
		return result;
	}
	
	private void addItemToRentalList(Item item) {
		int emptyIndex = -1;
		
		for (int i = 0; i < rentalItems.length; i++) {
			if (rentalItems[i] == null) {
				emptyIndex = i; //Found first slot avaliable
				break;
			}
		}
		
		if (emptyIndex == -1) {
			Item[] newArray = new Item[rentalItems.length + 1];
			
			for (int i = 0; i < rentalItems.length; i++) {
				newArray[i] = rentalItems[i];
			}
			
			emptyIndex = rentalItems.length;
			rentalItems = newArray;
		}
		
		rentalItems[emptyIndex] = item;
	}
}
