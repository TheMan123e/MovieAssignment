package s3722763.hireitems;

import s3722763.util.DateTime;
import s3722763.util.exceptions.BorrowException;

public class Game extends Item {
	private String[] platforms;
	private boolean extended;
	
	private final double STANDARD_FEE = 20;
	private final int BORROW_TIME = 22;
	
	public Game(String id, String title, String genre, String description, String[] platforms) {
		super(id, title, genre, description);
		this.platforms = platforms;
		extended = false;
	}

	@Override
	public String getDetails() {
		String result = "";
		result += String.format("ID:%13s%s\n", " ", id);
		result += String.format("Title:%10s%s\n", " ", title);
		result += String.format("Genre:%10s%s\n", " ", genre);
		result += String.format("Description:%4s%s\n", " ", description);
		
		result += String.format("Standard Fee:%3s$%1.2f\n", " ", STANDARD_FEE);
		
		if (isCurrentlyBorrowed()) {
			if (extended) {
				result += String.format("On loan:%8s%s\n", " ", "EXTENDED");
			} else {
				result += String.format("On loan:%8s%s\n", " ", "YES");
			}
		} else {
			result += String.format("On loan:%8s%s\n", " ", "NO");
		}
		
		result += getFormattedRecord();
		
		return result;
	}

	@Override
	public double borrow(String memberID) throws BorrowException {
		return borrow(memberID, -1);
	}
	
	@Override
	public double borrow(String memberID, int daysInAdvance) throws BorrowException{
		double fee = Double.NaN;
		if(!isCurrentlyBorrowed() || daysInAdvance > 0) {
			fee = STANDARD_FEE;
			int index = indexOfOldest();
			HiringRecord hr = new HiringRecord(fee);
			hr.borrowItem(getID(), memberID);
			
			if (daysInAdvance > 0) {
				DateTime today = new DateTime();
				hr.setDateBorrowed(new DateTime(today, daysInAdvance));
			}
			
			hireHistory[index] = hr;
			indexOfCurrentlyBorrowed = index;
		}
		
		return fee;
	}

	@Override
	public double returnItem(DateTime returnDate){
		double fee = Double.NaN;
		
		if (isCurrentlyBorrowed()) {
			int dayDiff = DateTime.diffDays(returnDate, hireHistory[indexOfCurrentlyBorrowed].getDateBorrowed());
			
			dayDiff -= BORROW_TIME;
			
			if (dayDiff > 0) {
				fee = dayDiff; //$1 each day past due
				int weeksOverdue = dayDiff / 7;
				fee += weeksOverdue * 5; //$5 each 7 days past due
				if (extended) {
					fee *= 0.5; //50% off if extended
				}
				
				hireHistory[indexOfCurrentlyBorrowed].returnItem(returnDate, fee);
				indexOfCurrentlyBorrowed = -1;
			} else {
				System.out.println("Ýou cannot return the game before you rented it");
			}
		}
		
		return fee;
	}

	@Override
	public DateTime getDateToReturn() {
		return new DateTime(hireHistory[indexOfCurrentlyBorrowed].getDateBorrowed(), BORROW_TIME);
	}
	
	public void extend() {
		this.extended = true;
	}
	
	public String toString() {
		String result = id + ":" + title + ":" + description + ":" + genre + ":";
		
		result += STANDARD_FEE + ":";	

		for(String platform : platforms) {
			result += platform + ", ";
		}
		
		result += ":";
		
		if (isCurrentlyBorrowed() && extended) {
			result += "E";
		} else if (isCurrentlyBorrowed()){
			result += "Y";
		} else {
			result += "N";
		}
		
		return result;
	}

}
