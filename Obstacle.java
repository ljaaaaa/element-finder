import java.util.*;

import javax.swing.ImageIcon;

public class Obstacle extends Object{

	ArrayList<Obstacle> projectiles;
	double tempTime, speed; //used for timing different things in different obstacles
	int flyCount, flyHeight, interval;

	//Spike & Snowballs
	public Obstacle(String imageName, int posX, int posY) {
		super (imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;
		tempTime = 0;
	}	

	//Laser Shooter/ Snow Shooter
	public Obstacle(String imageName, int posX, int posY, int interval) {
		super (imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;
		tempTime = 0;

		this.interval = interval;
		projectiles = new ArrayList<Obstacle>();
	}	

	//Bat
	public Obstacle(String imageName, int posX, int posY, char flyPattern) {
		super (imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;	
		tempTime = 0;
		flyCount = 10;

		switch (flyPattern) {
		case 'a':
			flyHeight = 5;
			break;
		case 'b':
			flyHeight = 7;
			break;

		case 'c':
			flyHeight = 10;
			break;
		default:
			flyHeight = 5;
			break;
		}
	}

	public void move(int speed) {
		posX += speed;
	}

	public void moveY(int speed) {
		posY += speed;
	}

	//Shoots lasers (shooters)
	public void shoot(int speed, char direction) {
		tempTime ++;

		//Lasers
		if (direction == 'l') {
			if (tempTime >= interval) {
				projectiles.add(new Obstacle("src/Laser.png", posX, posY+10));
				tempTime = 0;
		
			}	
			for (int x = 0; x < projectiles.size(); x++) {
				projectiles.get(x).move(speed);
			}	
		}

		//Snowballs
		if (direction == 's') {
			if (tempTime >= interval) {
				projectiles.add(new Obstacle("src/Snowball.png", posX+25, posY));
				tempTime = 0;
		
			}	
			for (int x = 0; x < projectiles.size(); x++) {
				projectiles.get(x).moveY(speed);
			}	
		}
	}

	//Makes object fly (bats)
	public void fly(int speed) {
		int neg;	
		String[] imageNames = {"src/Bat1.png", "src/Bat2.png"};

		ImageIcon icon = new ImageIcon(imageNames[(int)tempTime]);
		image = icon.getImage();

		tempTime += 0.5;
		if (tempTime == 2.0)
		{
			tempTime = 0;
		}

		if (flyCount >= -flyHeight){
			neg = 1;
			if (flyCount < 0){
				neg = -1;
			}
			posY -= (flyCount * flyCount) * 0.5 * neg;
			flyCount -= 1;
		}

		else {
			flyCount = flyHeight;
		}
		posX += speed;
	}
}