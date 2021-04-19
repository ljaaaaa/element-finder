import java.util.*;

public class Obstacle extends Object{
	
	ArrayList<Obstacle> lasers;
	int tempTime; //used for timing different things in different obstacles
	int laserSpeed;

	//Creates a basic obstacle
	public Obstacle(String imageName, int posX, int posY) {
		super (imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;
		tempTime = 0;
	}	

	//Creates a laser shooter
	public Obstacle(String imageName, int posX, int posY, int laserSpeed) {
		super (imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;
		tempTime = 0;
		
		this.laserSpeed = laserSpeed;
		lasers = new ArrayList<Obstacle>();
	}	
	
	//Moves an object a certain speed
	public void move(int speed) {
		posX += speed;
	}

	//Shoots lasers (shooters)
	public void shoot(ArrayList<Obstacle> lasers, int speed, int time) {
		tempTime ++;

		if (tempTime >= time ) {
			lasers.add(new Obstacle("src/Laser.png", posX, posY+10));
			tempTime = 0;
		}	

		for (int x = 0; x < lasers.size(); x++) {
			lasers.get(x).move(speed);
		}
	}
}