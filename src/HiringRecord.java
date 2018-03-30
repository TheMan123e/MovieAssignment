
public class HiringRecord {
	private String id;
	private double rentalFee;
	private double lateFee;
	private DateTime borrowDate;
	private DateTime returnDate;
	private boolean hasBeenReturned;
	
	public void borrowItem(String id, String memberID, double rentalFee) {
		this.rentalFee = rentalFee;
		this.borrowDate = new DateTime();
		this.hasBeenReturned = false;
		this.id = id + "_" + memberID + "_" + this.borrowDate.getEightDigitDate();
	}
	
	public void returnItem(DateTime returnDate, double lateFee) {
		this.returnDate = returnDate;
		this.lateFee = lateFee;
		this.hasBeenReturned = true;
	}
	
	public String getDetailsFormatted() {
		String result = "";
		result += String.format("Hire ID: %4s%s\n", " ", id);
		result += String.format("Borrow Date: %s\n", borrowDate.toString());
		
		if (hasBeenReturned) {
			//Seperated to make it easier to read
			result += String.format("Return Date: %s\n", borrowDate.toString());
			result += String.format("Fee: %8s$%1.2f\n", " ", rentalFee);
			result += String.format("Late Fee: %3s$%1.2f\n", " ", lateFee);
			
			double totalFee = rentalFee + lateFee;
			
			result += String.format("Total Fee: %s $%1.2f\n", " ", totalFee);
		}
		
		return result;
	}
	
	public String toString() {
		String result;
		
		if (hasBeenReturned) {
			result = String.format("%s:%s:%1.2f:%1.2f", id, returnDate.getEightDigitDate(), rentalFee, lateFee);
		} else {
			result = String.format("%s:%s:%s:%s", id, "none", "none", "none");
		}
		
		return result;
	}
	
	public boolean hasBeenReturned() {
		return hasBeenReturned;
	}
	
	public DateTime getDateBorrowed() {
		return borrowDate;
	}
	
	public DateTime getDateReturned() {
		return returnDate;
	}
	
	public double getRentalFee() {
		return rentalFee;
	}
}
