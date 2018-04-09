package s3722763;

import s3722763.hireitems.Item;
import s3722763.ui.MovieMaster;
import s3722763.ui.result.IResult;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void run() {
		MovieMaster master = new MovieMaster();
		master.loadMenu();
		master.displayOptions();
	}
}
