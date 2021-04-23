/* ORDER OF CLASSES (and components of each class):
 * (parent) OBJECT CLASS: signs
 * (child) CHARACTER CLASS: kid
 * (child) BACKGROUND CLASS: bg
 * (child) OBSTACLE CLASS: spikes, shooters, lasers, bats, snowballs, etc..
 * (child) ELEMENT CLASS: all elements
 */

public class Level{
	Element[] elements;
	Background bg;
	Obstacle[] obstacles, obstacles2;
	Object[] objects;
	int levelNum;

	//Constructor
	public Level(int levelNum) {
		this.levelNum = levelNum;
		objects = new Object[] {
			new Object("src/ThisWaySign.png", -300, 280)	
		};

		switch (levelNum) {
		case 1: //Level 1
			bg = new Background("src/backgrounds/SandyDesert.png", 0, 0);

			elements = new Element[] {
					new Element("src/elements/1-Hydrogen.png", 1000, 50),
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

			obstacles2 = new Obstacle[] {

			};

			break;

		case 2: //Level 2
			bg = new Background("src/backgrounds/DarkMountains.png", 0, 0);
			elements = new Element[] {
					new Element("src/elements/6-Carbon.png", 800, 300),
					new Element("src/elements/7-Nitrogen.png", 3000, 200),
					new Element("src/elements/8-Oxygen.png", 4500, 200),
					new Element("src/elements/9-Fluorine.png", 5800, 300),
					new Element("src/elements/10-Neon.png", 6500, 100),
			};

			obstacles = new Obstacle[] {
					new Obstacle("src/Shooter.png", 900, 450, 10),
					new Obstacle("src/Shooter.png", 1800, 100, 20),
					new Obstacle("src/Shooter.png", 2200, 340, 40),
					new Obstacle("src/Shooter.png", 2900, 470, 10),
					new Obstacle("src/Shooter.png", 4000, 50, 7),
					new Obstacle("src/Shooter.png", 5050, 490, 10),
					new Obstacle("src/Shooter.png", 5920, 380, 20),
					new Obstacle("src/Shooter.png", 6700, 230, 7)
			};

			obstacles2 = new Obstacle[] { 

			};

			break;

		case 3: //Level 3
			bg = new Background("src/backgrounds/DarkCave.png", 0, 0);
			elements = new Element[] {
					new Element("src/elements/11-Sodium.png", 800, 380),
					new Element("src/elements/12-Magnesium.png", 3000, 100),
					new Element("src/elements/13-Aluminum.png", 4500, 200),
					new Element("src/elements/14-Silicon.png", 5800, 300),
					new Element("src/elements/15-Phosphorus.png", 6800, 400),
			};

			obstacles = new Obstacle[] {
					new Obstacle("src/Bat1.png", 1000, 300, 'a'),
					new Obstacle("src/Bat1.png", 1500, 520, 'b'),
					new Obstacle("src/Bat1.png", 2200, 200, 'c'),
					new Obstacle("src/Bat1.png", 3000, 550, 'a'),
					new Obstacle("src/Bat1.png", 3700, 550, 'a'),
					new Obstacle("src/Bat1.png", 4500, 250, 'b'),
					new Obstacle("src/Bat1.png", 5000, 220, 'c'),
					new Obstacle("src/Bat1.png", 6000, 510, 'b'),
					new Obstacle("src/Bat1.png", 7000, 400, 'c')
			};

			obstacles2 = new Obstacle[] {

			};

			break;

		case 4: //Level 4
			bg = new Background("src/backgrounds/SnowyMountains.png", 0, 0);
			elements = new Element[] {
					new Element("src/elements/16-Sulfur.png", 900, 300),
					new Element("src/elements/17-Chlorine.png", 3000, 200),
					new Element("src/elements/18-Argon.png", 4500, 200),
					new Element("src/elements/19-Potassium.png", 5850, 200),
					new Element("src/elements/20-Calcium.png", 6500, 370),
			};

			obstacles = new Obstacle[] {
					new Obstacle("src/SnowShooter.png", 900, -150, 50),
					new Obstacle("src/SnowShooter.png", 1700, -150, 50),
					new Obstacle("src/SnowShooter.png", 2600, -150, 50),

					new Obstacle("src/SnowShooter.png", 3000, -150, 50),
					new Obstacle("src/SnowShooter.png", 4000, -150, 50),

					
					new Obstacle("src/SnowShooter.png", 6300, -150, 50),
			};

			obstacles2 = new Obstacle[] {
					new Obstacle("src/SnowSpike-100.png", 1250, 400),
					
					new Obstacle("src/SnowSpike-40.png", 2200, 460),
					new Obstacle("src/SnowSpike-40.png", 2240, 460),
					new Obstacle("src/SnowSpike-40.png", 2280, 460),
					
					new Obstacle("src/SnowSpike-50.png", 3350, 450),	
					new Obstacle("src/SnowSpike-50.png", 3400, 450),
					
					new Obstacle("src/SnowSpike-50.png", 4450, 450),
					new Obstacle("src/SnowSpike-50.png", 4500, 450),
					new Obstacle("src/SnowSpike-50.png", 4550, 450),
					new Obstacle("src/SnowSpike-50.png", 4600, 450),
					new Obstacle("src/SnowSpike-50.png", 4650, 450),
					
					new Obstacle("src/SnowSpike-100.png", 5800, 400),	
					new Obstacle("src/SnowSpike-100.png", 5900, 400),	


			};

			for (int x = 0; x < obstacles.length; x++) {
				obstacles[x].tempTime = obstacles[x].interval;
			}

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

			//Moves projectiles
			if (levelNum == 2 || levelNum == 4) {
				for (int y = 0; y < obstacles[x].projectiles.size(); y++) {
					obstacles[x].projectiles.get(y).move(speed);
				}	
			}
		}

		for (int x = 0; x < obstacles2.length; x++) {
			obstacles2[x].move(speed);
		}
		
		//Moves other objects
		for (int x = 0; x < objects.length; x++) {
			objects[x].move(speed);
		}
		
		
	}
}