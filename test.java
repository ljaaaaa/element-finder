import java.io.*;
import java.util.*;

public class test {
//This will determine the winners list and their score, and display on a leaderboard
	public static void main(String[] args) {

		File List = new File("src/Winners.txt");
		try {
			Scanner scanner = new Scanner(List);
			scanner.nextLine();
			
			String nextLine;
			ArrayList <String> winners = new ArrayList<String>();
			ArrayList <Double> times = new ArrayList<Double>();
			
			while(scanner.hasNextLine()) {
				nextLine = scanner.nextLine();
				String[] components = nextLine.split(",");
				winners.add(components[0]);
				times.add(Double.parseDouble(components[1]));
			}
			
			//Sorting
			double temp;
			String tempString;
			int j;
			boolean swapped = true;
			while (swapped) {
				swapped = false;
				for(j=0; j<times.size()-1; j++) {
					if (times.get(j) > times.get(j+1)) {
						//Switches times
						temp = times.get(j);
						times.set(j, times.get(j+1));
						times.set(j+1, temp);
						
						//Switches winners
						tempString = winners.get(j);
						winners.set(j, winners.get(j+1));
						winners.set(j+1, tempString);
						
						swapped = true;	
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
