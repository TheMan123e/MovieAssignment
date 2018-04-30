package s3722763.hireitems;
import s3722763.util.DateTime;

public class Movie extends Item {
	private boolean isNewRelease;
	
	//TODO: Set to something else
	private final double NEW_RELEASE_SURCHARGE = 10;
	private final int MAX_DAYS_NEW = 2;
	private final int MAX_DAYS = 7;
	private final double STANDARD_RENTAL_FEE_NEW = 5;
	private final double STANDARD_RENTAL_FEE = 3;
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
	
	@Override
	public double borrow(String memberID) {
		double fee = Double.NaN;
		
		if (!isCurrentlyBorrowed()) {
			int index = indexOfOldest();
			HiringRecord hr = new HiringRecord();
			//TODO: See if this changes with new release
			if (isNewRelease) {
				hr.borrowItem(id, memberID, STANDARD_RENTAL_FEE_NEW);
			} else {
				hr.borrowItem(id, memberID, STANDARD_RENTAL_FEE);
			}
			
			getHireHistory()[index] = hr;
			currentlyBorrowed = hr;
			isCurrentlyBorrowed = true;
			
			fee = hr.getRentalFee();
			
			System.out.println("Borrowed movie");
		} else {
			System.out.println("Book already borrowed");
		}
		
		return fee;
	}
	
	@Override
	public double returnItem(DateTime returnDate) {
		double fee = 0;
		
		if (isCurrentlyBorrowed())  {
			int daysBorrowed = DateTime.diffDays(returnDate, currentlyBorrowed.getDateBorrowed());
			
			if (daysBorrowed > 0) {
				fee = calculateFee(daysBorrowed);
				currentlyBorrowed.returnItem(returnDate, fee);
				
				isCurrentlyBorrowed = false;
			} else {
				System.out.println("The resulting days in negative, you can't give back a movie before you borrowed it");
				fee = Double.NaN;
			}
		} else {
			System.out.println("Movie has not been borrowed");
			fee = Double.NaN;
		}
		
		if (!isCurrentlyBorrowed) {
			//Means the above loop could return item
			int index = indexOfOldest();
			hireHistory[index] = new HiringRecord();
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
	
	public String toString() {
		String result = id + ":" + title + ":" + description + ":" + genre + ":";
		//TODO: Get rid of this
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
			result += String.format("Standard Fee:%3s$%f\n", " ", STANDARD_RENTAL_FEE_NEW);
		} else {
			result += String.format("Standard Fee:%3s$%f\n", " ", STANDARD_RENTAL_FEE);
		}
		
		result += String.format("On loan:%8s%b\n", " ", isCurrentlyBorrowed());
		
		if (isNewRelease) {
			result += String.format("Movie Type:%5s%s\n", " ", "New Release");
			result += String.format("Rental Period:%2s%s\n", " ", String.valueOf(MAX_DAYS_NEW) + " days");
		} else {
			result += String.format("Movie Type:%5s%s\n", " ", "Weekly");
			result += String.format("Rental Period:%2s%s\n", " ", String.valueOf(MAX_DAYS) + " days");
		}
		
		result += getFormattedRecord();
		
		return result;
	}

	@Override
	public DateTime getDateToReturn() {
		DateTime dt;
		
		if (isNewRelease) {
			dt = new DateTime(currentlyBorrowed.getDateBorrowed(), MAX_DAYS_NEW);
		} else {
			dt = new DateTime(currentlyBorrowed.getDateBorrowed(), MAX_DAYS);
		}
		
		return dt;
	}
}
