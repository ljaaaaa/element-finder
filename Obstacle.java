import java.util.*;

public class Obstacle extends Object{
	
	ArrayList<Obstacle> lasers;
	int tempTime; //used for timing different things in different obstacles

	public Obstacle(String imageName, int posX, int posY) {
		super (imageName, posX, posY);
	//	ArrayList<Obstacle> lasers;
		image = super.image;
		width = super.width;
		height = super.height;
		tempTime = 0;
		
	}	

	//Moves an object a certain speed
	public void move(int speed) {
		posX += speed;
	}

	//Creates a list of lasers (shooters)
	public void createLaserList () {
		lasers = new ArrayList<Obstacle>();
	}
	
	public ArrayList<Obstacle> getLaserList () {
		return lasers;
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