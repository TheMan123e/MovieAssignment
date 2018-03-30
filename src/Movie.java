
public class Movie {
	private String id;
	private String title;
	private String genre;
	private String description;
	private boolean isNewRelease;
	private HiringRecord currentlyBorrowed;
	private HiringRecord[] hireHistory;
	
	//TODO: Set to something else
	private final double NEW_RELEASE_SURCHARGE = 10;
}
