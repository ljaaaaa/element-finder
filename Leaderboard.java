import java.io.*;
import java.util.*;

public class Leaderboard {

	FileWriter fileWriter;
	File file;

	public static void main(String[] args) {
		Leaderboard l = new Leaderboard();
		l.viewWinners();
	}

	public Leaderboard(){
		try {
			fileWriter = new FileWriter("src/Winners.txt", true);	
			file = new File("src/Winners.txt");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addWinner(String name, double time) {
		try {
			fileWriter.write("\n");
			fileWriter.write(name + ": " + time);
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList <String> viewWinners() {
		ArrayList <String> list = new ArrayList<String>();

		try {
			Scanner scanner = new Scanner(file);
			int place = 1;
			scanner.nextLine();

			while(scanner.hasNextLine()) {
				String[] components = scanner.nextLine().split(":");
				list.add(place + ". " + components[0] + ":" + components[1]);
				place++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void sortFile(ArrayList<String> winners, ArrayList<Double> times) {
		//Sorting
		double temp; String tempString; int j; boolean swapped = true;
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
}