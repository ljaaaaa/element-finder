import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener, KeyListener{
	//Global Variables
	Character kid;
	Level level;
	JButton home, playAgain, nextLevel, start, directions, credits, startOver;
	JPanel cards, gamePanel, winPanel, homePanel;
	Modules modules;
	boolean goingLeft, goingRight, dead;
	CardLayout cl;
	Container container;
	final int speed, WIDTH, HEIGHT;

	//Run game
	public static void main(String[] args) {
		Main m = new Main();
		m.setUpWindow();
	}

	//Constructor
	public Main() {
		kid = new Character("src/characterR1.png", 550, 200);
		homePanel = new JPanel();
		gamePanel = this;
		winPanel = new JPanel();
		modules = new Modules();
		goingLeft = false;
		goingRight = true;
		dead = false;
		speed = 10;
		level = new Level(3); 
		WIDTH = 1200;
		HEIGHT = 600;

		Timer t1 = new Timer(100, this);
		t1.start();
	}

	public void keyPressed(KeyEvent event) {
		if (!dead) {
			if (KeyEvent.getKeyText(event.getKeyCode()) == "Space") { //Makes kid jump
				kid.Jumping = true;
			}

			if (KeyEvent.getKeyText(event.getKeyCode()) == "Left") { //Moves screen right
				if (kid.posX > level.objects[0].posX + level.objects[0].width) {
					level.bg.move(speed/4);
					goingLeft = true;
					goingRight = false;
					level.moveAll(speed);
				}
			}

			if (KeyEvent.getKeyText(event.getKeyCode()) == "Right") { //Moves screen left
				if (kid.posX + kid.width < level.objects[1].posX) {
					level.bg.move(-speed/4);
					goingRight = true;
					goingLeft = false;
					level.moveAll(-speed);
				}

			}
		}
	}
	public void keyReleased(KeyEvent event) { }

	public void keyTyped(KeyEvent event) { }

	//While True	
	public void actionPerformed(ActionEvent e) {	

		//Changes cards if necessary
		if (e.getSource() == home) { //Goes home
			cl.show(cards, "homeCard");
		}

		else if (e.getSource() == directions) { //Reading directions
			cl.show(cards, "directionsCard");
		}

		else if (e.getSource() == credits) { //Reading credits
			cl.show(cards, "creditsCard");		}


		if (e.getSource() == start || e.getSource() == playAgain || e.getSource() == startOver) { //Starts/ plays level
			dead = false;
			goingRight = true;
			goingLeft = false;

			level = new Level(level.levelNum);
			kid = new Character("src/characterR1.png", 550, 200);

			cl.show(cards, "gameCard");
			gamePanel.requestFocusInWindow();
		}

		if (level.elements.length == 0) { //Won level
			cl.show(cards, "winCard");

			level = new Level(level.levelNum);
			kid = new Character("src/characterR1.png", 550, 200);
		}

		else if (e.getSource() == nextLevel) { //Next level
			cl.show(cards, "gameCard");
			level = new Level(level.levelNum);
			gamePanel.requestFocusInWindow();
		}

		if (gamePanel.isVisible()) {
			//Checks if kid is jumping
			if (kid.Jumping) {
				kid.Jump();
			}

			switch (level.levelNum) { // !!! ~ Special things each level has done here

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
						if (modules.collided(kid, level.obstacles[x].projectiles.get(y), new Element("", 0, 0))){
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
						if (modules.collided(kid, level.obstacles[x].projectiles.get(y), new Element("", 0, 0))){
							dead = true;
						}
					}
				}
				break;

			case 5: //Lava Gyesers
				for (int x = 0; x < level.obstacles.length; x++) {
					level.obstacles[x].gloop();
				}
				break;

			}

			//Checks for element collisions			
			for (int x=0; x < level.elements.length; x++) {
				if (modules.collided(kid, new Obstacle("", 0, 0), level.elements[x])){
					level.elements = modules.removeElement(level.elements, level.elements[x]);
				}
			}

			//Checks for obstacle collisions
			for (int x=0; x < level.obstacles.length; x++) {
				if (modules.collided(kid, level.obstacles[x], new Element("", 0, 0))){
					dead = true;
				}				
			}

			for (int x = 0; x < level.obstacles2.length; x++) {
				if (modules.collided(kid, level.obstacles2[x], new Element("", 0, 0))){
					dead = true;
				}	
			}
		}

		repaint();
		Toolkit.getDefaultToolkit().sync();
	}

	//Paint Component
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

		JPanel directionsPanel = new JPanel();
		JPanel creditsPanel = new JPanel();	
		JPanel bookPanel = new JPanel();
		JPanel losePanel = new JPanel();
		
		//Home Panel	
		start = new JButton("Start");
		start.addActionListener(this);

		directions = new JButton("Directions");
		directions.addActionListener(this);

		credits = new JButton("Credits");
		credits.addActionListener(this);

		homePanel.add(start);
		homePanel.add(directions);
		homePanel.add(credits);

		//Game Panel
		gamePanel.addKeyListener(this);
		gamePanel.setFocusable(true);
		startOver = new JButton("Start Over");
		startOver.addActionListener(this);
		gamePanel.add(startOver);

		//Win Panel	
		home = new JButton("Home");
		home.addActionListener(this);

		playAgain = new JButton("Play Again");
		playAgain.addActionListener(this);

		nextLevel = new JButton("Next Level");
		nextLevel.addActionListener(this);

		winPanel.add(home);
		winPanel.add(playAgain);		
		winPanel.add(nextLevel);

		//Cards
		cards = new JPanel(new CardLayout());		
		cards.add(homePanel, "homeCard");
		cards.add(gamePanel, "gameCard");
		cards.add(directionsPanel, "directionsCard");
		cards.add(creditsPanel, "creditsCard");
		cards.add(bookPanel, "bookCard");
		cards.add(losePanel, "loseCard");
		cards.add(winPanel, "winCard");

		cl = (CardLayout)(cards.getLayout());

		f.add(cards, BorderLayout.CENTER);
		f.setSize(WIDTH, HEIGHT);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}