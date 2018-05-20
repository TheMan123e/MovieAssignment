package s3722763.hireitems;

import s3722763.util.DateTime;
import s3722763.util.exceptions.BorrowException;

/*
 * Class: Item
 * Description: Base class for hireable items
 * Author: Daniel Miskimmin	- 3722763
 */
public abstract class Item {
	//TODO: Maybe change these protected to only getters
	protected String id;
	protected String title;
	protected String description;
	protected String genre;
	protected double fee;
	protected HiringRecord[] hireHistory;
	protected int indexOfCurrentlyBorrowed;
	
	public Item(String id, String title, String genre, String description) {
		this.title = title;
		this.id = id;
		this.genre = genre;
		this.description = description;
		//In specifications only keep a max of 10 records
		this.hireHistory = new HiringRecord[10];
	}
	
	public DateTime getDayBorrowed() {
		if (!isCurrentlyBorrowed()) {
			System.out.println("Movie is not currently borrowed");
			return null;
		}
		
		int index = indexOfNewest();
		return hireHistory[index].getDateBorrowed();
	}
	
	protected String getFormattedRecord() {
		String result = "";
		result += String.format("%16s%s\n", " ", "BORROWING RECORD");
		result += String.format("%17s\n", "-");
		//Will become true is rented atleast once
		boolean hasBeenRented = false; 
		
		for(HiringRecord hr : hireHistory) {
			if (hr != null) {
				if (hr.hasBeenReturned() || hr.getDateBorrowed() != null) {
					result += hr.getDetailsFormatted();
					hasBeenRented = true;
				}
			}
		}
		
		if (!hasBeenRented) {
			result = "";
			result += String.format("%16s%s\n", " ", "BORROWING RECORD");
			result += String.format("%16s%s\n", " ", "NONE");
		}
		
		return result;
	}
	
	private int indexOfNewest() {
		DateTime now = new DateTime();
		int index = 0;
		int difference = 0;
		for (int i = 0; i < hireHistory.length; i++) {
			if (hireHistory[i] != null) {
				int tempDifference = DateTime.diffDays(now,
						hireHistory[i].getDateBorrowed());
				if (tempDifference < difference) {
					difference = tempDifference;
					index = i;
				}
			}
		}
		
		return index;
	}
	
	/*
	 * ALGORITHM
	 * BEGIN
	 * 		GET todays date
	 * 		FOR EACH item in hireHistory
	 * 			IF hiringrecord does not equal null
	 * 				IF difference in days is bigger than
	 * 				   previously bigger date diffference
	 * 				   SET biggest difference to new difference
	 * 				   SET biggest difference index to new index
	 *			IF hiring record is equal to null
	 *				SET index is equal to current index
	 *				RETURN this index
	 		RETURN index;
	 * END
	 */
	protected int indexOfOldest() {
		DateTime now = new DateTime();
		int index = 0;
		int difference = 0;
		for (int i = 0; i < hireHistory.length; i++) {
			if (hireHistory[i] != null) {
				int tempDifference = DateTime.diffDays(now, hireHistory[i].getDateReturned());
				if (tempDifference > difference) {
					difference = tempDifference;
					index = i;
				}
			} else {
				//Means not all of the 10 places in the array are filled so can get the position 
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public abstract String getDetails();
	public abstract double borrow(String memberID) throws BorrowException;
	public abstract double borrow(String memberID,  int daysInAdvance) throws BorrowException;
	public abstract double returnItem(DateTime returnDate);
	public abstract DateTime getDateToReturn();
	
	public HiringRecord[] getHireHistory() {
		return hireHistory;
	}
	
	public String getID() {
		return id;
	}
	
	public boolean isCurrentlyBorrowed() {
		if (indexOfCurrentlyBorrowed >= 0 && hireHistory[indexOfCurrentlyBorrowed] != null) {
			return true;
		} else {
			return false;
		}
	}

	public String getTitle() {
		return title;
	}
	
	public void setCurrentlyBorrowed(int index) {
		this.indexOfCurrentlyBorrowed = index;
	}
	
	public void setHiringRecord(HiringRecord[] hr) {
		hireHistory = hr;
 	}
}
