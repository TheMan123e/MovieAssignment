
public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void run() {
		HiringRecord record1 = new HiringRecord();
		HiringRecord record2 = new HiringRecord();
		
		record1.borrowItem("a", "b", 3);
		//record2.borrowItem("c", "d", 3);
		
		DateTime dt = new DateTime(4);
		System.out.println(record1.toString());
		
		record1.returnItem(dt, 20);
		
		System.out.println(record1.getDetailsFormatted());
		//System.out.println(record2.getDetails());
		System.out.println(record1.toString());
	}
}
