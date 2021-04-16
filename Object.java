import java.awt.*;
import javax.swing.*;
import java.util.*;

//This is the parent class of other objects (backgrounds, obstacles, etc.). A system of what belongs to which object child class has
//not been made yet.
public class Object {

	//Global Variables
	Image image;
	int posX, posY, width, height;
	
	//For shooters
	ArrayList<Obstacle> lasers;
	int tempTime; //for keeping track of when to shoot new laser;

	//Constructor
	public Object(String imageName, int posX, int posY) {
		ImageIcon icon = new ImageIcon(imageName);
		this.posX = posX;
		this.posY = posY;
		image = icon.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);

		lasers = new ArrayList<Obstacle>();
	}	

	//Moves an object a certain speed
	public void move(int speed) {
		posX += speed;
	}

	//Only for shooters
	public void shoot(int speed, int time) {
		tempTime ++;

		//adds new lasers to list that will be shot
		if (tempTime >= time ) {
			lasers.add(new Obstacle("src/Laser.png", posX, posY+10));
			tempTime = 0;
		}	

		//shoots all available lasers
		for (int x = 0; x < lasers.size(); x++) {
			lasers.get(x).move(speed);
		}
	}
}