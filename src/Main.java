
public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void run() {
		Movie movie = new Movie("M_MOS", "Man of Steel", "Action", "Description", false);
		
		movie.borrow("member");
		
		DateTime dt = new DateTime(10);
		
		double fee = movie.returnItem(dt);
		System.out.println("Fee was " + fee);
	}
}
