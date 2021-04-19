/* ORDER OF CLASSES (and components of each class):
 * (parent) OBJECT CLASS: 
 * (child) CHARACTER CLASS: kid
 * (child) BACKGROUND CLASS: bg
 * (child) OBSTACLE CLASS: spikes, shooters, lasers
 * (child) ELEMENT CLASS: elements
 */

public class Level{
	Element[] elements;
	Background bg;
	Obstacle[] obstacles;
	int levelNum;
	int[] laserSpeeds;

	//Constructor
	public Level(int levelNum) {
		this.levelNum = levelNum;

		switch (levelNum) {
		case 1: //Level 1
			bg = new Background("src/backgrounds/SandyDesert.png", 0, 0);

			elements = new Element[] {
					new Element("src/elements/1-Hydrogen.png", 1000, 0),
					new Element("src/elements/2-Helium.png", 3000, 100),
					new Element("src/elements/3-Lithium.png", 4500, 200),
					new Element("src/elements/4-Beryllium.png", 5800, 250),
					new Element("src/elements/5-Boron.png", 6200, 200) 
			};

			obstacles = new Obstacle[] {
					new Obstacle("src/Spike-50.png", 900, 450),

					new Obstacle("src/Spike-40.png", 1540, 460),
					new Obstacle("src/Spike-40.png", 1580, 460),
					new Obstacle("src/Spike-40.png", 1620, 460),

					new Obstacle("src/Spike-50.png", 3000, 450),
					new Obstacle("src/Spike-50.png", 3050, 450),

					new Obstacle("src/Spike-100.png", 4470, 400),

					new Obstacle("src/Spike-50.png", 5730, 450),
					new Obstacle("src/Spike-50.png", 6000, 450) 
			};
			break;

		case 2: //Level 2
			bg = new Background("src/backgrounds/TieDye.png", 0, 0);
			elements = new Element[] {
					new Element("src/elements/6-Carbon.png", 800, 300),
					new Element("src/elements/7-Nitrogen.png", 3000, 200),
					new Element("src/elements/8-Oxygen.png", 4500, 100),
					new Element("src/elements/9-Fluorine.png", 5800, 300),
					new Element("src/elements/10-Neon.png", 6500, 100),
			};

			obstacles = new Obstacle[] {
					new Obstacle("src/Shooter.png", 900, 450, 10),
					new Obstacle("src/Shooter.png", 1800, 100, 10),
					new Obstacle("src/Shooter.png", 2000, 150, 10),
					new Obstacle("src/Shooter.png", 2900, 470, 10),
					new Obstacle("src/Shooter.png", 4000, 50, 10),
					new Obstacle("src/Shooter.png", 5050, 490, 10),
					new Obstacle("src/Shooter.png", 5920, 380, 10),
					new Obstacle("src/Shooter.png", 6700, 110, 10)
			};

			break;

		case 3:
			break;

		case 4:
			break;

		case 5:
			break;
		}
	}
	
	public void moveAll(int speed) {
		//Moves background
		bg.move(speed/4);

		//Moves elements
		for (int x = 0; x < elements.length; x++) {
			elements[x].move(speed);
		}

		//Moves obstacles
		for (int x = 0; x < obstacles.length; x++) {
			obstacles[x].move(speed);

			//Moves shooters
			if (levelNum == 2) {
				for (int y = 0; y < obstacles[x].lasers.size(); y++) {
					obstacles[x].lasers.get(y).move(speed);
				}	
			}
		}
	}
}