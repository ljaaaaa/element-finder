import java.util.*;

public class Level{
	Object[] elements, obstacles, allObjects, lasers;
	Background bg;
	int levelNum;

//Constructor
	public Level(int levelNum) {
		this.levelNum = levelNum;

		switch (levelNum) {
		case 1: //Level 1
			bg = new Background("src/backgrounds/SandyDesert.png", 0, 0);
			
			elements = new Object[] {
					new Object("src/elements/1-Hydrogen.png", 1000, 0),
					new Object("src/elements/2-Helium.png", 3000, 100),
					new Object("src/elements/3-Lithium.png", 4500, 200),
					new Object("src/elements/4-Beryllium.png", 5800, 200),
					new Object("src/elements/5-Boron.png", 6200, 200) 
			};

			obstacles = new Object[] {
					new Object("src/Spike-50.png", 900, 450),

					new Object("src/Spike-40.png", 1540, 460),
					new Object("src/Spike-40.png", 1580, 460),
					new Object("src/Spike-40.png", 1620, 460),

					new Object("src/Spike-50.png", 3000, 450),
					new Object("src/Spike-50.png", 3050, 450),

					new Object("src/Spike-100.png", 4470, 400),

					new Object("src/Spike-50.png", 5730, 450),
					new Object("src/Spike-50.png", 6000, 450) 
			};
			allObjects = new Object[elements.length + obstacles.length];
			break;

		case 2: //Level 2
			bg = new Background("src/backgrounds/DarkMountains.png", 0, 0);
			elements = new Object[] {
					new Object("src/elements/6-Carbon.png", 1000, 0),
					new Object("src/elements/7-Nitrogen.png", 3000, 200),
					new Object("src/elements/8-Oxygen.png", 4500, 200),
					new Object("src/elements/9-Fluorine.png", 5800, 200),
					new Object("src/elements/10-Neon.png", 6200, 200),
			};
			
			obstacles = new Object[] { //each of these shooters will be shooting lasers at different speeds 
					new Object("src/Shooter.png", 900, 450),
			};
			
			lasers = new Object[] {
			};
			
			allObjects = new Object[elements.length + obstacles.length];
			break;

		case 3:
			break;

		case 4:
			break;

		case 5:
			break;
			
		}
		
		refresh();
	}

	public void refresh() {
		Object[] emptyArray;
		int tempPos = 0; //for keeping track when combining arrays

		emptyArray  = new Object[elements.length + obstacles.length];		
		allObjects = emptyArray;

		for (int x=0; x < elements.length; x++) {
			allObjects[tempPos] = elements[x];
			tempPos++;
		}

		for (int x=0; x < obstacles.length; x++){
			allObjects[tempPos] = obstacles[x];
			tempPos++;
		}
		tempPos = 0;
	}
}