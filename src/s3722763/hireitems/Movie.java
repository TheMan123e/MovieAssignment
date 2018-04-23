package s3722763.hireitems;
import s3722763.util.DateTime;

public class Movie extends Item {
	private boolean isNewRelease;
	
	//TODO: Set to something else
	private final double NEW_RELEASE_SURCHARGE = 10;
	private final int MAX_DAYS_NEW = 2;
	private final int MAX_DAYS = 7;
	private final double STANDARD_RENTAL_FEE = 1;
	
	/**
	 * 
	 * @param id M_id
	 * @param title
	 * @param genre
	 * @param description
	 */
	public Movie(String id, String title, String genre, String description, boolean newRelease) {
		super(id, title, genre, description);
		this.isNewRelease = newRelease;
	}
	
	public double borrow(String memberID) {
		double fee = Double.NaN;
		
		if (!isCurrentlyBorrowed()) {
			int index = indexOfOldest();
			HiringRecord hr = new HiringRecord();
			//TODO: See if this changes with new release
			hr.borrowItem(id, memberID, STANDARD_RENTAL_FEE);
			
			getHireHistory()[index] = hr;
			currentlyBorrowed = hr;
			
			fee = hr.getRentalFee();
			
			System.out.println("Borrowed movie");
		} else {
			System.out.println("Book already borrowed");
		}
		
		return fee;
	}
	
	public double returnItem(DateTime returnDate) {
		double fee = 0;
		
		if (isCurrentlyBorrowed())  {
			int daysBorrowed = DateTime.diffDays(returnDate, currentlyBorrowed.getDateBorrowed());
			
			if (daysBorrowed > 0) {
				fee = calculateFee(daysBorrowed);
			} else {
				System.out.println("The resulting days in negative, you can't give back a movie before you borrowed it");
				fee = Double.NaN;
			}
		} else {
			System.out.println("Movie has not been borrowed");
			fee = Double.NaN;
		}
		
		return fee;
	}
	
	private double calculateFee(int days) {
		double fee = 0;
		int daysOver = 0;
		
		if (isNewRelease) {
			daysOver = days - MAX_DAYS_NEW;
			fee += NEW_RELEASE_SURCHARGE;
		} else {
			daysOver = days - MAX_DAYS;
		}
		
		fee += daysOver * (0.5 * currentlyBorrowed.getRentalFee());
		
		return fee;
	}
	
	private int indexOfOldest() {
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
			}
		}
		
		return index;
	}
	
	private boolean isCurrentlyBorrowed() {
		for(HiringRecord hr : hireHistory) {
			if ((hr != null) &&(!hr.hasBeenReturned())) {
				return true;
			}
		}
		
		return false;
	}
	
	public String toString() {
		String result = id + ":" + title + ":" + description + ":" + genre + ":" + STANDARD_RENTAL_FEE + ":";
		
		if (isNewRelease) {
			result += "NR";
		} else {
			result += "WL";
		}
		
		if (isCurrentlyBorrowed()) {
			result += ":" + "Y";
		} else {
			result += ":" + "N";
		}
		
		return result;
	}
	
	@Override
	public String getDetails() {
		String result = "";
		result += String.format("ID:%10s%s\n", " ", id);
		result += String.format("Title:%10s%s\n", " ", title);
		result += String.format("Genre:%10s%s\n", " ", genre);
		result += String.format("Description:%10s%s\n", " ", description);
		result += String.format("Standard Fee:%10s$%s\n", " ", String.valueOf(fee));
		result += String.format("On loan:%5s%b\n", " ", isCurrentlyBorrowed);
		
		if (isNewRelease) {
			result += String.format("Movie Type:%10s%s\n", " ", "New Release");
			result += String.format("Rental Period:%10s%s\n", " ", String.valueOf(MAX_DAYS_NEW) + " days");
		} else {
			result += String.format("Movie Type:%10s%s\n", " ", "Weekly");
			result += String.format("Rental Period:%10s%s\n", " ", String.valueOf(MAX_DAYS) + " days");
		}
		
		result += getFormattedRecord();
		
		return result;
	}
}
