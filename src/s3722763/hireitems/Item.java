package s3722763.hireitems;

import s3722763.util.DateTime;

public abstract class Item {
	//TODO: Maybe change these protected to only getters
	protected String id;
	protected String title;
	protected String description;
	protected String genre;
	protected double fee;
	protected HiringRecord[] hireHistory;
	protected boolean isCurrentlyBorrowed;
	protected HiringRecord currentlyBorrowed;
	
	public Item(String id, String title, String genre, String description) {
		this.title = title;
		this.id = id;
		this.genre = genre;
		this.description = description;
		//In specifications only keep a max of 10 records
		this.hireHistory = new HiringRecord[10];
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
	
	protected int indexOfOldest() {
		DateTime now = new DateTime();
		int index = 0;
		int difference = 0;
		
		//TODO: Check if there is an index with nothing there, if so then put the hire into that index
		for (int i = 0; i < hireHistory.length; i++) {
			if (hireHistory[i] != null) {
				int tempDifference = DateTime.diffDays(now, hireHistory[i].getDateReturned());
				System.out.println(tempDifference);
				if (tempDifference > difference) {
					difference = tempDifference;
					index = i;
				}
			} else {
				//Means not all of the 10 places in the array are filled so can get the position of the null
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public abstract String getDetails();
	public abstract double borrow(String memberID);
	public abstract double returnItem(DateTime returnDate);
	public abstract DateTime getDateToReturn();
	
	public HiringRecord[] getHireHistory() {
		return hireHistory;
	}
	
	public String getID() {
		return id;
	}
	
	public boolean isCurrentlyBorrowed() {
		return isCurrentlyBorrowed;
	}

	public String getTitle() {
		return title;
	}
}
