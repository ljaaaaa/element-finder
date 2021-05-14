import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Modules {

	//Set up  a button
	public JButton setUpButton(ActionListener actionListener, String name, int x, int y, int width, int height) {
		JButton button = new JButton(name);
		button.setBounds(x, y, width, height);
		button.addActionListener(actionListener);
		return button;
	}
	
	//Writes in file
	public void writeFile(String fileName, String text) {
		try {
			FileWriter fileWriter = new FileWriter(fileName, true);	
			fileWriter.write("\n");
			fileWriter.write(text);
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Reads file
	public ArrayList<String> readFile(String fileName) {
		ArrayList<String> stuff = new ArrayList<String>(); //Arraylist of text in file
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				stuff.add(scanner.nextLine());
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return stuff;
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

		if(((cLeft >= tLeft && cLeft <= tRight) ||  //If objects have collided on x axis (left and right sides)
				(cRight >= tLeft && cRight <= tRight) ||
				(cLeft >= tLeft && cRight <= tRight) || 
				(cLeft <= tLeft && cRight >= tRight)) &&

				((cTop >= tTop && cTop <= tBottom) ||  //If objects have collided on y axis (top and bottom sides)
						(cBottom <= tBottom && cBottom >= tTop) ||
						(cTop >= tBottom && cBottom <= tBottom ) ||
						(cTop <= tBottom && cBottom >= tBottom))) {
			return true;
		}
		return false;
	}
}