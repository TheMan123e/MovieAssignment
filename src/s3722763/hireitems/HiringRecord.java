package s3722763.hireitems;
import s3722763.util.DateTime;

public class HiringRecord {
	private String id;
	private double rentalFee;
	private double lateFee;
	private DateTime borrowDate;
	private DateTime returnDate;
	/**
	 * @param id
	 * @param dateReturned
	 * @param rentalFee
	 * @param lateFee
	 */
	public HiringRecord(String id, int dateReturned, double rentalFee, double lateFee) {
		String[] idElements = id.split("_");
		String dateBorrowedStr = idElements[3];
		int dateBorrowed = Integer.parseInt(dateBorrowedStr);
		
		this.id = id;
		this.rentalFee = rentalFee;
		this.lateFee = lateFee;
		borrowDate = new DateTime();
		borrowDate.setDate(dateBorrowed);
		returnDate = new DateTime();
		returnDate.setDate(dateReturned);
	}
	
	public HiringRecord(String id) {
		String[] idElements = id.split("_");
			//4th element of the id has t he 8 digit date
		String dateBorrowedStr = idElements[3];
		int dateBorrowed = Integer.parseInt(dateBorrowedStr);
		this.id = id;
		borrowDate = new DateTime();
		borrowDate.setDate(dateBorrowed);
	}
	
	public HiringRecord(double fee) {
		this.rentalFee = fee;
	}
	
	/**
	 * @param id
	 * @param memberID
	 */
	public void borrowItem(String id, String memberID) {
		this.borrowDate = new DateTime();
		this.id = id + "_" + memberID + "_" + this.borrowDate.getEightDigitDate();
	}
	
	public void returnItem(DateTime returnDate, double lateFee) {
		this.returnDate = returnDate;
		this.lateFee = lateFee;
	}
	
	public String getDetailsFormatted() {
		String result = "";
		result += String.format("%16sHire ID: %4s%s\n", " ", " ", id);
		result += String.format("%16sBorrow Date: %s\n", " ", borrowDate.getFormattedDate());
		
		if (returnDate != null) {
			//Seperated to make it easier to read
			result += String.format("%16sReturn Date: %s\n", " ", borrowDate.getFormattedDate());
			result += String.format("%16sFee: %8s$%1.2f\n", " ", " ", rentalFee);
			result += String.format("%16sLate Fee: %3s$%1.2f\n", " ", " ", lateFee);
			
			double totalFee = rentalFee + lateFee;
			
			result += String.format("%16sTotal Fee: %s $%1.2f\n", " ", " ", totalFee);
			result += String.format("%16s%s\n", " ", "-");
		}

		return result;
	}
	
	public String toString() {
		String result;
		
		if (returnDate != null) {
			result = String.format("%s:%s:%1.2f:%1.2f", id, returnDate.getEightDigitDate(), rentalFee, lateFee);
		} else {
			result = String.format("%s:%s:%s:%s", id, "none", "none", "none");
		}
		
		return result;
	}
	
	public boolean hasBeenReturned() {
		return returnDate != null;
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

	public void setDateBorrowed(DateTime day) {
		this.borrowDate = day;
	}
}
