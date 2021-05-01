import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File List = new File("src/Winners.txt");
		try {
			Scanner scanner = new Scanner(List);
			
			String nextLine = scanner.nextLine();
			
			System.out.println("next line: " + nextLine);
			
			String[] listOfWinners = nextLine.split(","); 
		}
		catch (FileNotFoundException e) {
			System.out.println("Something went wrong :(");
			e.printStackTrace();
		}

	}
	
	public class MyClock{

		long startTime, minutes, seconds;
		public MyClock() {
			startTime = System.currentTimeMillis();

			minutes = (System.currentTimeMillis() - startTime) /1000/ 60;
			seconds = (System.currentTimeMillis() - startTime) /1000 % 60;
		
			String time = minutes + ":" + seconds;

		}

		public void goTime() {
			minutes = (System.currentTimeMillis() - startTime) /1000/ 60;
			seconds = (System.currentTimeMillis() - startTime) /1000 % 60;
		}

		public String getTime() {
			String time = minutes + ":" + seconds;
			return time;
		}
	}

}
