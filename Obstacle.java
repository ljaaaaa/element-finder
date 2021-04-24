import java.util.*;
import javax.swing.*;

public class Obstacle extends Object{

	ArrayList<Obstacle> projectiles;
	double tempTime, speed;
	int flyCount, flyHeight, interval, lavaHeight, lavaSpeed;;
	boolean goingUp, goingDown;
	Obstacle lava;

	//Spikes & Snowballs
	public Obstacle(String imageName, int posX, int posY) {
		super (imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;
		tempTime = 0;
	}	

	//Gyesers
	public Obstacle(String imageName, int posX, int posY, int lavaSpeed, int lavaHeight) {
		super (imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;
		tempTime = 0;
		
		goingUp = true;
		goingDown = false;
		lava = new Obstacle("src/Lava.png", posX - 17, posY - height - 100);
		this.lavaHeight = lavaHeight;
		this.lavaSpeed = lavaSpeed;
	}
	
	//Laser & Snow Shooters
	public Obstacle(String imageName, int posX, int posY, int interval) {
		super (imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;
		tempTime = 0;

		this.interval = interval;
		projectiles = new ArrayList<Obstacle>();
	}	

	//Bats
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

	//Sends lava into the air
	public void gloop() {
		if (goingUp) {
			lava.posY -= lavaSpeed;
		}
		
		else if (goingDown) {
			lava.posY += lavaSpeed;
		}
	
		if (lava.posY <= lavaHeight) {
			goingDown = true;
			goingUp = false;
		}
		
		else if (lava.posY >= posY - 50) {
			goingUp = true;
			goingDown = false;
		}
		
	}
	
	//Shoots projectiles
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

	//Makes object fly
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