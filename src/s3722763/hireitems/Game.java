package s3722763.hireitems;

import s3722763.util.DateTime;

public class Game extends Item {
	private String[] platforms;
	private boolean extended;
	
	public Game(String id, String title, String genre, String description) {
		super(id, title, genre, description);
	}

	@Override
	public String getDetails() {
		return null;
	}

	@Override
	public double borrow(String memberID) {
		return 0;
	}

	@Override
	public double returnItem(DateTime returnDate) {
		return 0;
	}

	@Override
	public DateTime getDateToReturn() {
		return null;
	}

}
