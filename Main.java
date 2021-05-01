import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Main extends JPanel implements ActionListener, KeyListener{
	//Global Variables
	Character kid;
	Modules modules;
	MyClock clock;
	Level level;

	JButton bookHome, creditsHome, directionsHome, winHome, loseHome;
	JButton winPlayAgain, losePlayAgain, nextLevel, start, directions, credits, elements;
	JLabel time;

	JPanel cards, gamePanel;
	MyPanel homePanel, bookPanel, elementPanel, winPanel, losePanel, creditsPanel, directionsPanel;

	boolean goingLeft, goingRight, dead;
	final int speed, WIDTH, HEIGHT;

	CardLayout cl;

	ArrayList <Element> collectedElements;
	ArrayList <String> collectedNames;
	ArrayList <JButton> elementButtons;

	//Runs game
	public static void main(String[] args) {
		Main m = new Main();
		m.setUpWindow();
	}

	//Constructor
	public Main() {
		kid = new Character("src/characterR1.png", 550, 200);
		modules = new Modules();
		clock = new MyClock();
		level = new Level(1);

		gamePanel = this;
		homePanel = new MyPanel("src/backgrounds/HomePicture.png");
		winPanel = new MyPanel("src/backgrounds/YouWin.png");
		losePanel = new MyPanel("src/backgrounds/YouLose.png");
		bookPanel = new MyPanel("src/backgrounds/TreasureChest.png");
		elementPanel = new MyPanel("src/backgrounds/TreasureChest.png");
		directionsPanel = new MyPanel("");
		creditsPanel = new MyPanel("");	

		goingLeft = false;
		goingRight = true;
		dead = false;

		speed = 10; 
		WIDTH = 1200;
		HEIGHT = 600;

		collectedElements = new ArrayList <Element>();
		collectedNames = new ArrayList <String>();
		elementButtons = new ArrayList <JButton>();

		Timer t1 = new Timer(100, this);
		t1.start();
	}

	//Key Listener *** Listenes To Keys
	public void keyPressed(KeyEvent event) {
		if (!dead) {
			if (KeyEvent.getKeyText(event.getKeyCode()) == "Space") { //Makes kid jump
				kid.Jumping = true;
			}

			if (KeyEvent.getKeyText(event.getKeyCode()) == "Left") { //Moves screen right
				if (kid.posX > level.objects[0].posX + level.objects[0].width) {
					goingLeft = true;
					goingRight = false;
					level.moveAll(speed);
				}
			}

			if (KeyEvent.getKeyText(event.getKeyCode()) == "Right") { //Moves screen left
				if (kid.posX + kid.width < level.objects[1].posX) {
					goingRight = true;
					goingLeft = false;
					level.moveAll(-speed);
				}
			}
		}
	}
	public void keyReleased(KeyEvent event) { }

	public void keyTyped(KeyEvent event) { }

	//Main Loop *** Makes stuff move
	public void actionPerformed(ActionEvent e) {	

		//Won Level
		if (level.elements.length == 0) {
			level = new Level(level.levelNum);
			kid = new Character("src/characterR1.png", 550, 200);
			cl.show(cards, "winCard");
			winPanel.repaint();
		}

		//Lose Level
		else if (dead) {
			cl.show(cards, "loseCard");
			losePanel.repaint();
		}

		//Element Facts
		for (int x = 0; x < elementButtons.size(); x++) {
			if (e.getSource() == elementButtons.get(x)) {
				cl.show(cards, "elementCard");
				elementPanel.removeAll();
		//		System.out.println(x);
		//		System.out.println(collectedElements.get(x).imageName);
				collectedElements.get(x).showFact(elementPanel, this);
			}
		}

		//Book Of Elements
		for (int x = 0; x < collectedElements.size(); x++) {
			if (e.getSource() == collectedElements.get(x).back) {
				cl.show(cards, "bookCard");	
			}
		}

		//Home
		if (e.getSource() == bookHome || e.getSource() == creditsHome || 
				e.getSource() == directionsHome || e.getSource() == winHome || e.getSource() == loseHome) {
			goingRight = true;
			goingLeft = false;
			dead = false;

			level = new Level(level.levelNum);
			kid = new Character("src/characterR1.png", 550, 200);
			cl.show(cards, "homeCard");
		}

		//Elements Book
		else if (e.getSource() == elements) {
			bookPanel.setLayout(new GridLayout(5, 7, 3, 3));
			bookPanel.removeAll();
			elementButtons.clear();

			bookHome = new JButton("Home");
			bookHome.addActionListener(this);
			bookPanel.add(bookHome);

			//Add spaces for first row
			for (int x = 0; x < 6; x++) {
				bookPanel.add(new JLabel(""));
			}
			
			System.out.println(collectedElements.size());
			//Add elements onto screen
			for (int x = 0; x < collectedElements.size(); x++) {
				ImageIcon tempIcon = new ImageIcon(collectedNames.get(x));
				elementButtons.add(new JButton(tempIcon));
				elementButtons.get(x).addActionListener(this);
				bookPanel.add(elementButtons.get(x));
			}
			System.out.println(elementButtons.size());
		
			//Adds empty space for all missing elements
			for (int x = 0; x < (28 - collectedElements.size()); x++) {
				bookPanel.add(new JLabel(""));
			}
			cl.show(cards, "bookCard");
		}

		//Directions
		else if (e.getSource() == directions) {
			cl.show(cards, "directionsCard");
		}

		//Credits
		else if (e.getSource() == credits) {
			cl.show(cards, "creditsCard");		}


		//Plays Game
		else if (e.getSource() == start || e.getSource() == winPlayAgain || e.getSource() == losePlayAgain) {
			dead = false;
			goingRight = true;
			goingLeft = false;

			level = new Level(level.levelNum);
			kid = new Character("src/characterR1.png", 550, 200);

			cl.show(cards, "gameCard");
			gamePanel.requestFocusInWindow();
		}

		//Next Level
		if (e.getSource() == nextLevel) {
			if (level.levelNum <= 4) {
				level.levelNum ++;
				cl.show(cards, "gameCard");
				level = new Level(level.levelNum);
				gamePanel.requestFocusInWindow();	
			}
		}

		//Checks for element collisions			
		for (int x=0; x < level.elements.length; x++) {
			if (modules.collided(kid, new Obstacle("", 0, 0), level.elements[x])){

				if (!collectedNames.contains(level.elements[x].imageName)) {
					collectedNames.add(level.elements[x].imageName);
					collectedElements.add(level.elements[x]);
				}
				level.elements = modules.removeElement(level.elements, level.elements[x]);
			}
		}

		//Checks for obstacle collisions
		/*
		for (int x=0; x < level.obstacles.length; x++) {
			if (modules.collided(kid, level.obstacles[x], new Element("", 0, 0, 0))){
				dead = true;
			}				
		}

		for (int x = 0; x < level.obstacles2.length; x++) {
			if (modules.collided(kid, level.obstacles2[x], new Element("", 0, 0, 0))){
				dead = true;
			}	
		}
		 */

		//Moves Objects
		if (gamePanel.isVisible()) {
			//Runs timer
			time.setText(clock.getTime());

			//Checks if kid is jumping
			if (kid.Jumping) {
				kid.Jump();
			}

			//Level Obstacles
			switch (level.levelNum) {
			case 1:
				break;

			case 2: //Laser Shooters
				for (int x = 0; x < level.obstacles.length; x++) {
					if (modules.onScreen(level.obstacles[x], WIDTH, HEIGHT)) {
						level.obstacles[x].shoot(-speed, 'l');	
					}
					else {
						level.obstacles[x].projectiles.clear();
					}

					for (int y = 0; y < level.obstacles[x].projectiles.size(); y++) {
						if (modules.collided(kid, level.obstacles[x].projectiles.get(y), new Element("", 0, 0, 0))){
							dead = true;
						}
					}
				}

				break;

			case 3: //Bats
				for (int x=0; x < level.obstacles.length; x++) {
					if (modules.onScreen(level.obstacles[x], WIDTH, HEIGHT)) {
						level.obstacles[x].fly(-speed);
					}
				}
				break;

			case 4: //Snow Shooters
				for (int x = 0; x < level.obstacles.length; x++) {
					if (modules.onScreen(level.obstacles[x], WIDTH, HEIGHT)) {
						level.obstacles[x].shoot(speed, 's');	
					}

					else {
						level.obstacles[x].projectiles.clear();
					}

					for (int y = 0; y < level.obstacles[x].projectiles.size(); y++) {
						if (modules.collided(kid, level.obstacles[x].projectiles.get(y), new Element("", 0, 0, 0))){
							dead = true;
						}
					}
				}
				break;

			case 5: //Lava Gyesers
				for (int x = 0; x < level.obstacles.length; x++) {
					level.obstacles[x].gloop();
				}

				for (int x = 0; x < level.obstacles.length; x++) {
					if (modules.collided(kid, level.obstacles[x].lava, new Element("", 0, 0, 0))){
						dead = true;
					}	
				}
				break;
			}
		}

		//Repaints Screen
		repaint();
		Toolkit.getDefaultToolkit().sync();
	}

	//Paint Component *** Paints stuff on screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//Background
		g2d.drawImage(level.bg.image, level.bg.posX, 0, null);
		g2d.drawImage(level.bg.image, level.bg.posX - level.bg.width, 0, null);

		//Elements
		for (int x = 0; x < level.elements.length; x++) {
			Element tempElement = level.elements[x];
			g2d.drawImage(tempElement.image, tempElement.posX, tempElement.posY, null); }

		//Lasers
		if (level.levelNum == 2 || level.levelNum == 4) {
			for (int x = 0; x < level.obstacles.length; x++) {
				for (int y = 0; y < level.obstacles[x].projectiles.size(); y++) {
					Obstacle tempArray = level.obstacles[x].projectiles.get(y);
					g2d.drawImage(tempArray.image, tempArray.posX, tempArray.posY, null); 
				}	
			}
		}

		//Obstacles
		for (int x = 0; x < level.obstacles.length; x++) {
			Obstacle tempObstacle = level.obstacles[x];

			if (level.levelNum == 5) {
				g2d.drawImage(tempObstacle.lava.image, tempObstacle.lava.posX, tempObstacle.lava.posY, null); 
			}

			g2d.drawImage(tempObstacle.image, tempObstacle.posX, tempObstacle.posY, null); 
		}

		for (int x = 0; x < level.obstacles2.length; x++) {
			Obstacle tempObstacle = level.obstacles2[x];
			g2d.drawImage(tempObstacle.image, tempObstacle.posX, tempObstacle.posY, null); 
		}

		//Objects
		for (int x = 0; x < level.objects.length; x++) {
			Object tempObject = level.objects[x];
			g2d.drawImage(tempObject.image, tempObject.posX, tempObject.posY, null); 
		}

		//Character
		if (dead) {
			kid.Animate("dead"); 
		}

		else if (goingLeft) {
			kid.Animate("left"); 
		}

		else if (goingRight) {
			kid.Animate("right"); 
		}
		g2d.drawImage(kid.image, kid.posX, kid.posY, null);
	}

	//Sets Up Window	
	public void setUpWindow() {
		JFrame f = new JFrame("Element Finder");

		//Credits Panel
		creditsHome = new JButton("Home");
		creditsHome.addActionListener(this);
		creditsPanel.add(creditsHome);

		//Directions Panel
		directionsHome = new JButton("Home");
		directionsHome.addActionListener(this);
		directionsPanel.add(directionsHome);

		//Home Panel		
		homePanel.setLayout(null);

		start = new JButton("Start");
		start.setBounds(500, 120, 200, 75);
		start.addActionListener(this);
		homePanel.add(start);

		directions = new JButton("Directions");
		directions.setBounds(350, 220, 150, 50);
		directions.addActionListener(this);
		homePanel.add(directions);

		credits = new JButton("Credits");
		credits.setBounds(525, 220, 150, 50);
		credits.addActionListener(this);
		homePanel.add(credits);

		elements = new JButton("My Elements");
		elements.setBounds(700, 220, 150, 50);
		elements.addActionListener(this);
		homePanel.add(elements);

		//Game Panel
		time = new JLabel(clock.getTime());
		gamePanel.add(time);

		gamePanel.addKeyListener(this);
		gamePanel.setFocusable(true);

		//Win Panel	
		winPanel.setLayout(null);

		winHome = new JButton("Home");
		winHome.setBounds(350, 150, 150, 50);
		winHome.addActionListener(this);
		winPanel.add(winHome);

		winPlayAgain = new JButton("Play Again");
		winPlayAgain.setBounds(525, 150, 150, 50);
		winPlayAgain.addActionListener(this);
		winPanel.add(winPlayAgain);		

		nextLevel = new JButton("Next Level");
		nextLevel.setBounds(700, 150, 150, 50);
		nextLevel.addActionListener(this);
		winPanel.add(nextLevel);

		//Lose Panel
		losePanel.setLayout(null);

		loseHome = new JButton("Home");
		loseHome.setBounds(425, 150, 150, 50);
		loseHome.addActionListener(this);
		losePanel.add(loseHome);

		losePlayAgain = new JButton("Play Again");
		losePlayAgain.setBounds(625, 150, 150, 50);
		losePlayAgain.addActionListener(this);
		losePanel.add(losePlayAgain);

		//Cards
		cards = new JPanel(new CardLayout());		
		cards.add(homePanel, "homeCard");
		cards.add(gamePanel, "gameCard");
		cards.add(directionsPanel, "directionsCard");
		cards.add(creditsPanel, "creditsCard");
		cards.add(bookPanel, "bookCard");
		cards.add(winPanel, "winCard");
		cards.add(losePanel, "loseCard");
		cards.add(elementPanel, "elementCard");
		cl = (CardLayout)(cards.getLayout());

		f.add(cards, BorderLayout.CENTER);
		f.setSize(WIDTH, HEIGHT);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}