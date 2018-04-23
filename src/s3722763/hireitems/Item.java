package s3722763.hireitems;

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
		
		for(HiringRecord hr : hireHistory) {
			if (hr != null) {
				result += hr.getDetailsFormatted();
			}
		}
		
		return result;
	}
	
	public abstract String getDetails();
	
	public HiringRecord[] getHireHistory() {
		return hireHistory;
	}
	
	public String getID() {
		return id;
	}
	

}
