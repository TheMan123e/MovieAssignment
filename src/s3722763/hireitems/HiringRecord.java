package s3722763.hireitems;
import s3722763.util.DateTime;

public class HiringRecord {
	private String id;
	private double rentalFee;
	private double lateFee;
	private DateTime borrowDate;
	private DateTime returnDate;
	
	public HiringRecord() {}
	public HiringRecord(String id, int dateReturned, double rentalFee, double lateFee) {
		String[] idElements = id.split("_");
		String dateBorrowedStr = idElements[3];
		int dateBorrowed = Integer.parseInt(dateBorrowedStr);
		
		this.id = id;
		this.rentalFee = rentalFee;
		this.lateFee = lateFee;
		borrowDate = getDateFromInt(dateBorrowed);
		returnDate = getDateFromInt(dateReturned);
	}
	
	public void borrowItem(String id, String memberID, double rentalFee) {
		this.rentalFee = rentalFee;
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
	
	//TODO: Fix calculates wrong datetime
	public DateTime getDateFromInt(int date) {
		int day = date / 1000000;
		int month = (date / 10000) - (day * 1000000);
		int year = date - (day * 1000000) - (month * 10000);
		
		return new DateTime(day, month, year);
	}
}
