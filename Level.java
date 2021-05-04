/* ORDER OF CLASSES (and components of each class):
 * (parent) OBJECT CLASS: signs
 * (child) CHARACTER CLASS: kid
 * (child) BACKGROUND CLASS: bg
 * (child) OBSTACLE CLASS: spikes, shooters, lasers, bats, snowballs, lava
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
		
		switch (levelNum) {
		case 1: //Level 1
			bg = new Background("src/backgrounds/SandyDesert.png", 0, 0);

			elements = new Element[] {
					new Element("src/elements/1-Hydrogen.png", 1.008, 1000, 50),
					new Element("src/elements/2-Helium.png", 4.003, 3000, 100),
					new Element("src/elements/3-Lithium.png", 6.941, 4500, 200),
					new Element("src/elements/4-Beryllium.png", 9.012, 5800, 250),
					new Element("src/elements/5-Boron.png", 10.811, 6300, 200) 
			};
						
			elements[0].fact = "Hydrogen is the lightest and most common element in the universe. It's also very reactive!";
			elements[1].fact = "Helium is used to make balloons float, as it is lighter than oxygen and safer than hydrogen.";
			elements[2].fact = "Lithium is a soft silvery metal, and is the lightest metal there is. It's so light, it can float on water!";
			elements[3].fact = "Berylium is very rare, but can be found in volcanic rocks. However it is very toxic and harmful to humans.";
			elements[4].fact = "Boron burns with a green flame, and can be used to make green fireworks."; 
			
			obstacles = new Obstacle[] {
					new Obstacle("src/Spike-50.png", 900, 450),
					new Obstacle("src/Spike-40.png", 1540, 460),
					new Obstacle("src/Spike-40.png", 1580, 460),
					new Obstacle("src/Spike-40.png", 1620, 460),
					new Obstacle("src/Spike-50.png", 3000, 450),
					new Obstacle("src/Spike-50.png", 3050, 450),
					new Obstacle("src/Spike-100.png", 4470, 400),
					new Obstacle("src/Spike-50.png", 5730, 450),
					new Obstacle("src/Spike-50.png", 6010, 450) 
			};
			obstacles2 = new Obstacle[] {

			};

			break;

		case 2: //Level 2
			bg = new Background("src/backgrounds/DarkMountains.png", 0, 0);
			elements = new Element[] {
					new Element("src/elements/6-Carbon.png", 12.011, 800, 390),
					new Element("src/elements/7-Nitrogen.png", 14.007, 3000, 200),
					new Element("src/elements/8-Oxygen.png", 15.999, 4500, 200),
					new Element("src/elements/9-Fluorine.png", 18.998, 5800, 300),
					new Element("src/elements/10-Neon.png", 20.180, 6500, 100),
			};
			
			elements[0].fact = "Carbon is found in all living organisms, and is the basis for organic chemistry.";
			elements[1].fact = "Dentists use Nitrogen for laughing gas. It is also the most common element in our atmosphere!";
			elements[2].fact = "Oxygen is very important, as we use it to breathe. Earth has more oxygen than any other planet in our solar system.";
			elements[3].fact = "Fluoring is the most reactive element, and it will react with almost any element!";
			elements[4].fact = "Ever heard of 'Neon Signs', that's because they likely have neon inside them, to make them light up!";
			
			obstacles = new Obstacle[] {
					new Obstacle("src/Shooter.png", 910, 450, 10),
					new Obstacle("src/Shooter.png", 1800, 100, 20),
					new Obstacle("src/Shooter.png", 2200, 340, 40),
					new Obstacle("src/Shooter.png", 2900, 470, 10),
					new Obstacle("src/Shooter.png", 4000, 50, 7),
					new Obstacle("src/Shooter.png", 5050, 490, 10),
					new Obstacle("src/Shooter.png", 5920, 380, 20),
					new Obstacle("src/Shooter.png", 6700, 130, 7)
			};

			obstacles2 = new Obstacle[] { 

			};

			break;

		case 3: //Level 3
			bg = new Background("src/backgrounds/DarkCave.png", 0, 0);
			elements = new Element[] {
					new Element("src/elements/11-Sodium.png", 22.990, 800, 380),
					new Element("src/elements/12-Magnesium.png", 24.305, 3000, 100),
					new Element("src/elements/13-Aluminum.png", 26.982, 4500, 200),
					new Element("src/elements/14-Silicon.png", 28.086, 5800, 300),
					new Element("src/elements/15-Phosphorus.png", 30.974, 6800, 400),
			};
			
			elements[0].fact = "Sodium makes up about 2.6% of the Earth's crust!";
			elements[1].fact = "Magnesium fires can burn in nitrogen, carbon dioxide, and water, making them very difficult to put out.";
			elements[2].fact = "Aluminum can be recycled! Even after being recycled, it keeps the same physical properties.";
			elements[3].fact = "Silicon expands when it freezes in water, it also has a very high melting point.";
			elements[4].fact = "White phosphorus is very reactive, and can cause burns if touched.";
			
			obstacles = new Obstacle[] {
					new Obstacle("src/Bat1.png", 1000, 300, 5.0),
					new Obstacle("src/Bat1.png", 1500, 520, 7.0),
					new Obstacle("src/Bat1.png", 2200, 200, 10.0),
					new Obstacle("src/Bat1.png", 3000, 550, 5.0),
					new Obstacle("src/Bat1.png", 3700, 550, 5.0),
					new Obstacle("src/Bat1.png", 4500, 250, 7.0),
					new Obstacle("src/Bat1.png", 5000, 220, 10.0),
					new Obstacle("src/Bat1.png", 6000, 510, 7.0),
					new Obstacle("src/Bat1.png", 7000, 400, 10.0)
			};

			obstacles2 = new Obstacle[] {

			};

			break;

		case 4: //Level 4
			bg = new Background("src/backgrounds/SnowyMountains.png", 0, 0);
			elements = new Element[] {
					new Element("src/elements/16-Sulfur.png", 30.974, 900, 300),
					new Element("src/elements/17-Chlorine.png", 35.435, 3000, 200),
					new Element("src/elements/18-Argon.png", 39.948, 4500, 200),
					new Element("src/elements/19-Potassium.png", 39.098, 5850, 200),
					new Element("src/elements/20-Calcium.png", 40.078, 6500, 390),
			};
			
			elements[0].fact = "Sulfur is pale yellow and odorless. One of Jupiter's moons looks yellow due to the amount of Sulfur in it's atomosphere.";
			elements[1].fact = "Chlorine is often added to swimming pools to kill germs, however it has many other uses too!";
			elements[2].fact = "Argon is 'inert' meaning it doesn't usually react with other elements. It is one of the Noble Gases.";
			elements[3].fact = "Bananas, chocolate, nuts, and avocados are all good sources of Potassium!";
			elements[4].fact = "The sun gives you Vitamin D, which we need to absorb Calcium.";
			
			obstacles = new Obstacle[] {
					new Obstacle("src/SnowShooter.png", 900, -150, 50),
					new Obstacle("src/SnowShooter.png", 1700, -150, 55),
					new Obstacle("src/SnowShooter.png", 2600, -150, 50),
					new Obstacle("src/SnowShooter.png", 3000, -150, 55),
					new Obstacle("src/SnowShooter.png", 4000, -150, 50),

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
			bg = new Background("src/backgrounds/LavaLand.png", 0, 0);

			elements = new Element[] {
					new Element("src/elements/21-Scandium.png", 44.956, 1100, 200),
					new Element("src/elements/22-Titanium.png", 44.88, 3000, 50),
					new Element("src/elements/23-Vanadium.png", 50.942, 4500, 200),
					new Element("src/elements/24-Chromium.png", 51.996, 5800, 250),
					new Element("src/elements/25-Manganese.png", 54.845, 6900, 200) 
			};
			
			elements[0].fact = "Madagascar and Norway are thought to be the only places on Earth with high amounts of Scandium.";
			elements[1].fact = "Titanium is the only element that will burn in pure Nitrogen gas.";
			elements[2].fact = "Vanadium can be purple, green, blue, or yellow, depending on it's oxidation state.";
			elements[3].fact = "Chromium is a hard silvery metal. Additionally, some Chromium compounds are poisonous.";
			elements[4].fact = "Manganese is very brittle, and has properties similar to Iron. Manganese is mostly found in the Earth's crust.";
			
			obstacles = new Obstacle[] {
					new Obstacle("src/LavaShooter.png", 910, 450, 5, 100),	
					new Obstacle("src/LavaShooter.png", 2000, 450, 6, 50),
					new Obstacle("src/LavaShooter.png", 3000, 450, 4, 150),
					new Obstacle("src/LavaShooter.png", 4000, 450, 3, 150),
					new Obstacle("src/LavaShooter.png", 4400, 450, 4, 150),
					new Obstacle("src/LavaShooter.png", 5300, 450, 6, 150),
					new Obstacle("src/LavaShooter.png", 6500, 450, 8, 300)			
			};

			obstacles2 = new Obstacle[] {
			};

			break;
		}

		objects = new Object[] {
				new Object("src/ThisWaySign.png", -300, 280),
				new Object("src/ThatWaySign.png", elements[4].posX + 500, 280)
		};
	}

	public void moveAll(int speed) {
		//Moves Background
		bg.move(speed/4);

		//Moves Elements
		for (int x = 0; x < elements.length; x++) {
			elements[x].move(speed);
		}

		//Moves Obstacles
		for (int x = 0; x < obstacles.length; x++) {
			obstacles[x].move(speed);

			//Moves Projectiles
			if (levelNum == 2 || levelNum == 4) {
				for (int y = 0; y < obstacles[x].projectiles.size(); y++) {
					obstacles[x].projectiles.get(y).move(speed);
				}	
			}

			//Moves Lava
			if (levelNum == 5) {
				obstacles[x].lava.move(speed);
			}
		}

		for (int x = 0; x < obstacles2.length; x++) {
			obstacles2[x].move(speed);
		}

		//Moves Other Objects
		for (int x = 0; x < objects.length; x++) {
			objects[x].move(speed);
		}
	}
}