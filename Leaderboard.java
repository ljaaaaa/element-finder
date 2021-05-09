import java.io.*;
import java.util.*;

public class Leaderboard {
	FileWriter fileWriter;
	File file;
	ArrayList <String> winners;
	ArrayList <Double> times;

	public Leaderboard(){
		winners = new ArrayList<String>();
		times = new ArrayList<Double>();
		
		try {
			fileWriter = new FileWriter("src/Winners.txt", true);	
			file = new File("src/Winners.txt");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addWinner(String name, double time, int minutes, int seconds) {
		try {
			fileWriter.write("\n");
			fileWriter.write(time + "~" + minutes + ":" + seconds + " - " + name);
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList <String> viewWinners() {
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			times.clear();
			winners.clear();
			while(scanner.hasNextLine()) {
				String[] components = scanner.nextLine().split("~");
				times.add(Double.parseDouble(components[0]));
				winners.add(components[1]);
			}
			winners = sortWinners();
			scanner.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return winners;
	}

	public ArrayList<String> sortWinners() {
		double temp; 
		String tempString; 
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for(int j=0; j<times.size()-1; j++) {
				if (times.get(j) > times.get(j+1)) {
					
					//Switches times & names
					temp = times.get(j);
					times.set(j, times.get(j+1));
					times.set(j+1, temp);
					
					tempString = winners.get(j);
					winners.set(j, winners.get(j+1));
					winners.set(j+1, tempString);
					
					swapped = true;	
				}
			}
		}
		return winners;
	}
}