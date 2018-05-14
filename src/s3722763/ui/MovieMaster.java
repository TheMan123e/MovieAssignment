package s3722763.ui;

import java.io.IOException;

import s3722763.hireitems.HiringRecord;
import s3722763.hireitems.Item;
import s3722763.hireitems.Movie;
import s3722763.ui.menu.Menu;
import s3722763.ui.menu.actions.Action;
import s3722763.ui.menu.actions.ActionResult;
import s3722763.util.FileHandler;

public class MovieMaster {	
	private final String DELIMITER = "#";
	private Item[] rentalItems;
	private Menu menu;
	
	/*
	 * Load menu
	 * Get user key
	 * find menu item with that key
	 * 
	 * 
	 */
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
	
	private void loadRentalItems() throws IOException {
		FileHandler fileHandler = new FileHandler();
		//Movies
		String movies = fileHandler.load("movies");
		String fileDelimiter = fileHandler.getDelimiter();
		String[] moviesList = movies.split(fileDelimiter);
		
		for(String id : moviesList) {
			if (!id.equals("") && !id.equals("null")) {
				String info = fileHandler.load(id);
				String[] infoSorted = info.split(":");
				
				boolean isNewRelease = true;
				if(infoSorted[5].equals("WL")) {
					isNewRelease = false;
				}
				
				Movie movie = new Movie(infoSorted[0], infoSorted[1], infoSorted[3], infoSorted[2], isNewRelease);
				
				String rentalData = fileHandler.load(movie.getID() + "_hiring_info");
				if (!rentalData.equals("")) {
					String[] rental = rentalData.split(fileHandler.getDelimiter());
					for(String s : rental) {
						//String id, String memberID, double rentalFee) 
						String[] elements = s.split(":");
						if (!elements[0].equals("null")) {
							HiringRecord hr = new HiringRecord(elements[0], Integer.valueOf(elements[1]),
									Double.valueOf(elements[2]), Double.valueOf(elements[3]));
							movie.addToHiringRecord(hr);
						}
					}
				}
				
				HiringRecord hr = new HiringRecord();
				movie.addToHiringRecord(hr);
				
				addItemToRentalList(movie);
			}
		}
		
		//Games
	}
	
	private void saveRentalItems() throws IOException {
		FileHandler fileHandler = new FileHandler();
		
		String[] movieIDs = new String[1];
		
		for(Item item : rentalItems) {
			if (item instanceof Movie) {
				movieIDs = addItemToStringArray(item.getID(), movieIDs);
				String movieInfo = item.toString();
				HiringRecord[] hireInfo = item.getHireHistory();
				
				String hiringInfo = "";
				
				for(HiringRecord hr : hireInfo) {
					if (hr != null) {
						hiringInfo += hr.toString() + fileHandler.getDelimiter();
					}
				}
				
				fileHandler.save(movieInfo, item.getID());
				fileHandler.save(hiringInfo, item.getID() + "_hiring_info");
			}
		}
		
		String moviesList = "";
		for(String s : movieIDs) {
			moviesList += s + fileHandler.getDelimiter();
		}
		
		fileHandler.save(moviesList, "movies");
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
			Item[] newArray = new Item[rentalItems.length];
			
			for (int i = 0; i < rentalItems.length; i++) {
				newArray[i] = rentalItems[i];
			}
			
			emptyIndex = rentalItems.length;
			rentalItems = newArray;
		}
		
		rentalItems[emptyIndex] = item;
	}
}
