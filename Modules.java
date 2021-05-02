import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Modules {

	public void writeFile(String fileName, String text) {
		try {
			FileWriter fileWriter = new FileWriter(fileName);	
			fileWriter.write(text);
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFile(String fileName) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			scanner.nextLine();

			ArrayList <String> list = new ArrayList<String>();

			while(scanner.hasNextLine()) {
				String[] components = scanner.nextLine().split(",");
				list.add(components[0] + ":" + components[1]);
			}	
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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

	//Removes an element from element array
	public Element[] removeElement(Element[] array, Element item) {
		Element[] newArray = new Element[array.length-1];
		int pos = 0;

		for (int x = 0; x < newArray.length; x++) {
			if (array[pos] != item) {
				newArray[x] = array[pos];
			}
			else {
				newArray[x] = array[pos+1];
				pos++;
			}
			pos++;
		}

		return newArray;
	}

	//Checks if obstacle is on screen
	public boolean obstacleOnScreen(Obstacle o, int screenWidth, int screenHeight) {
		if (o.posX <= screenWidth && o.posX + o.width >= 0 &&
				o.posY <= screenHeight && o.posY + o.height >= 0 ) {
			return true;
		}
		return false;		
	}

	//Checks if character has collided with an object
	public boolean collided(Character c, Obstacle o, Element e) {

		int cLeft = c.posX;
		int cRight = c.posX + c.width;
		int cTop = c.posY;
		int cBottom = c.posY + c.height;

		int tLeft = 0, tRight = 0, tTop = 0, tBottom = 0;

		if (o.width == -1) { //checks collision with character and element
			tLeft = e.posX;
			tRight = e.posX + e.width;
			tTop = e.posY;
			tBottom = e.posY + e.height;
		}

		else if (e.width == -1){ //checks collision with character and object
			tLeft = o.posX; 
			tRight = o.posX + o.width;
			tTop = o.posY;
			tBottom = o.posY + o.height;
		}

		if(((cLeft >= tLeft && cLeft <= tRight) ||  //Left & right collisions
				(cRight >= tLeft && cRight <= tRight) ||
				(cLeft >= tLeft && cRight <= tRight) || 
				(cLeft <= tLeft && cRight >= tRight)) &&

				((cTop >= tTop && cTop <= tBottom) ||  //Top & bottom collisions
						(cBottom <= tBottom && cBottom >= tTop) ||
						(cTop >= tBottom && cBottom <= tBottom ) ||
						(cTop <= tBottom && cBottom >= tBottom))) {
			return true;
		}
		return false;
	}
}