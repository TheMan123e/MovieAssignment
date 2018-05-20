package s3722763.hireitems;
import s3722763.util.DateTime;
import s3722763.util.exceptions.BorrowException;

/*
 * Class: Movie
 * Description: This class is the class which represents hireable items
 * Author: Daniel Miskimmin	- 3722763
 */
public class Movie extends Item {
	private boolean isNewRelease;

	private final int MAX_DAYS_NEW = 2;
	private final int MAX_DAYS = 7;
	private final double STANDARD_RENTAL_FEE_NEW = 5;
	private final double STANDARD_RENTAL_FEE = 3;
	
	/**
	 * @param id M_id
	 * @param title
	 * @param genre
	 * @param description
	 */
	public Movie(String id, String title, String genre, String description, boolean newRelease) {
		super(id, title, genre, description);
		this.isNewRelease = newRelease;
	}

	@Override
	public double borrow(String memberID) throws BorrowException {
		return borrow(memberID, -1);
	}
	
	/*
	 * ALGORITHM
	 * BEGIN
	 * 		GET memberID and the days until the movie is to be borrowed
	 * 		IF isn't borrowed or days in advance is greater than zero
	 * 			GET indexOfOldest hiring record
	 * 			GET fee for rental
	 * 			CREATE new hiring record
	 * 			IF days in advance is greater than 0
	 * 				SET date to be borrowed to the day x number of days in advance
	 * 		ELSE
	 * 			THROW exception that item has already been borrowed
	 * END
	 * 
	 */
	@Override
	public double borrow(String memberID, int daysInAdvance) throws BorrowException{
		double fee = Double.NaN;
		if (!isCurrentlyBorrowed() || daysInAdvance > 0) {
			int index = indexOfOldest();
			
			fee = getFee();
			
			HiringRecord hr = new HiringRecord(fee);
			hr.borrowItem(id, memberID);
			
			if (daysInAdvance > 0) {
				DateTime today = new DateTime();
				hr.setDateBorrowed(new DateTime(today, daysInAdvance));
			}			

			getHireHistory()[index] = hr;
			indexOfCurrentlyBorrowed = index;
			
			System.out.println("Borrowed movie");
		} else {
			throw new BorrowException(getID(), "already borrowed");
		}
		
		return fee;
	}
	
	@Override
	public double returnItem(DateTime returnDate) {
		double fee = 0;
		
		if (isCurrentlyBorrowed())  {
			int daysBorrowed = DateTime.diffDays(returnDate, 
					hireHistory[indexOfCurrentlyBorrowed].getDateBorrowed());
			
			if (daysBorrowed > 0) {
				fee = calculateFee(daysBorrowed);
				hireHistory[indexOfCurrentlyBorrowed].returnItem(returnDate, fee);
				indexOfCurrentlyBorrowed = -1;	
			} else {
				System.out.println("The resulting days in negative,"
						+ " you can't give back a movie before you borrowed it");
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
		} else {
			daysOver = days - MAX_DAYS;
		}
		
		if (daysOver > 0) {
			fee += daysOver * (0.5 * hireHistory[indexOfCurrentlyBorrowed].getRentalFee());
		}
		
		return fee;
	}
	
	public String toString() {
		String result = id + ":" + title + ":" + description + ":" + genre + ":";
		if (isNewRelease)
			result += STANDARD_RENTAL_FEE_NEW + ":";
		else {
			result += STANDARD_RENTAL_FEE + ":";	
		}
		
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
		result += String.format("ID:%13s%s\n", " ", id);
		result += String.format("Title:%10s%s\n", " ", title);
		result += String.format("Genre:%10s%s\n", " ", genre);
		result += String.format("Description:%4s%s\n", " ", description);
		
		if (isNewRelease) {
			result += String.format("Standard Fee:%3s$%1.2f\n",
					" ", STANDARD_RENTAL_FEE_NEW);
		} else {
			result += String.format("Standard Fee:%3s$%1.2f\n", " ", 
					STANDARD_RENTAL_FEE);
		}
		
		if (isCurrentlyBorrowed()) {
			result += String.format("On loan:%8s%s\n", " ", "YES");
		} else {
			result += String.format("On loan:%8s%s\n", " ", "NO");
		}
		
		if (isNewRelease) {
			result += String.format("Movie Type:%5s%s\n", " ", "New Release");
			result += String.format("Rental Period:%2s%s\n", " ",
					String.valueOf(MAX_DAYS_NEW) + " days");
		} else {
			result += String.format("Movie Type:%5s%s\n", " ", "Weekly");
			result += String.format("Rental Period:%2s%s\n", " ",
					String.valueOf(MAX_DAYS) + " days");
		}
		
		result += getFormattedRecord();
		
		return result;
	}


	@Override
	public DateTime getDateToReturn() {
		DateTime dt;
		
		if (isNewRelease) {
			dt = new DateTime(hireHistory[indexOfCurrentlyBorrowed].getDateBorrowed(), 
					MAX_DAYS_NEW);
		} else {
			dt = new DateTime(hireHistory[indexOfCurrentlyBorrowed].getDateBorrowed(),
					MAX_DAYS);
		}
		
		return dt;
	}

	public double getFee() {
		if (isNewRelease) {
			return STANDARD_RENTAL_FEE_NEW;
		} else {
			return STANDARD_RENTAL_FEE;
		}
	}
}
