import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener, KeyListener{

	//Global Variables
	Character kid;
	Level level;
	JButton home, playAgain, nextLevel, start, directions, credits;
	JPanel cards, gamePanel, winPanel, homePanel;
	Modules modules;
	boolean goingLeft, goingRight, dead;
	CardLayout cl;
	int speed;
	Container container;
	final int WIDTH, HEIGHT;
	
	//Run game
	public static void main(String[] args) {
		Main m = new Main();
		m.setUpWindow(m);
	}

	//Constructor
	public Main() {
		kid = new Character("src/characterR1.png", 550, 200);
		homePanel = new JPanel();
		gamePanel = new JPanel();
		winPanel = new JPanel();
		modules = new Modules();
		goingLeft = false;
		goingRight = true;
		dead = false;
		speed = 20;
		level = new Level(2);
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
				level.bg.move(speed/4);
				goingLeft = true;
				goingRight = false;

				level.moveAll(speed);
			}

			if (KeyEvent.getKeyText(event.getKeyCode()) == "Right") { //Moves screen left
				level.bg.move(-speed/4);
				goingRight = true;
				goingLeft = false;

				level.moveAll(-speed);

			}
		}
	}
	public void keyReleased(KeyEvent event) { }

	public void keyTyped(KeyEvent event) { }

	//While True	
	public void actionPerformed(ActionEvent e) {	
		//Checks kid
		if (kid.Jumping) {
			kid.Jump();
		}

		//Buttons
		if (e.getSource() == home) { //Goes home
			cl.show(cards, "homeCard");
		}

		else if (e.getSource() == directions) { //Reading directions
			cl.show(cards, "directionsCard");
		}

		else if (e.getSource() == credits) { //Reading credits
			cl.show(cards, "creditsCard");		}


		if (e.getSource() == start || e.getSource() == playAgain) { //Starts/ plays level
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
			level = new Level(2);
			gamePanel.requestFocusInWindow();
		}

		if (gamePanel.isVisible()) {
		
			//Shoots lasers here (level 2)
			if (level.levelNum == 2) {
				for (int x = 0; x < level.obstacles.length; x++) {
					if (modules.onScreen(level.obstacles[x], WIDTH, HEIGHT)) {
						level.obstacles[x].shoot(level.obstacles[x].lasers, -speed, level.obstacles[x].laserSpeed);
					}
					else {
						level.obstacles[x].lasers.clear();
					}
				}
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
					goingLeft = false;
					goingRight = false;
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

		if (gamePanel.isVisible()) {
			//Background
			g2d.drawImage(level.bg.image, level.bg.posX, 0, null);
			g2d.drawImage(level.bg.image, level.bg.posX - level.bg.width, 0, null);

			//Lasers
			if (level.levelNum == 2) {
				
				for (int x = 0; x < level.obstacles.length; x++) {
					for (int y = 0; y < level.obstacles[x].lasers.size(); y++) {
						Obstacle tempArray = level.obstacles[x].lasers.get(y);
						g2d.drawImage(tempArray.image, tempArray.posX, tempArray.posY, null); 
					}	
				}
			}

			//Elements
			for (int x = 0; x < level.elements.length; x++) {
				Element tempElement = level.elements[x];
				g2d.drawImage(tempElement.image, tempElement.posX, tempElement.posY, null); }
		}

		//Obstacles
		for (int x = 0; x < level.obstacles.length; x++) {
			Obstacle tempObstacle = level.obstacles[x];
			g2d.drawImage(tempObstacle.image, tempObstacle.posX, tempObstacle.posY, null); 
		}

		//Character
		if (goingLeft) {
			kid.Animate("left"); 
		}

		else if (goingRight) {
			kid.Animate("right"); 
		}

		else if (dead) {
			kid.Animate("dead"); 
		}

		g2d.drawImage(kid.image, kid.posX, kid.posY, null);
	}

	//Sets Up Window	
	public void setUpWindow(Main main) {
		JFrame f = new JFrame("Element Finder");

		JPanel directionsPanel = new JPanel();
		JPanel creditsPanel = new JPanel();	
		JPanel bookPanel = new JPanel();
		JPanel losePanel = new JPanel();
		JLabel screenName = new JLabel("Screen Name");

		//Home Panel		
		screenName = new JLabel("Home Screen");
		start = new JButton("Start");
		start.addActionListener(this);

		directions = new JButton("Directions");
		directions.addActionListener(this);

		credits = new JButton("Credits");
		credits.addActionListener(this);

		homePanel.add(screenName);
		homePanel.add(start);
		homePanel.add(directions);
		homePanel.add(credits);

		//Game Panel
		gamePanel.add(main);
		gamePanel.setLayout(new GridLayout(1, 1));
		gamePanel.addKeyListener(this);
		gamePanel.setFocusable(true);

		//Win Panel	
		screenName = new JLabel("Win Screen");
		home = new JButton("Home");
		home.addActionListener(this);

		playAgain = new JButton("Play Again");
		playAgain.addActionListener(this);

		nextLevel = new JButton("Next Level");
		nextLevel.addActionListener(this);

		winPanel.add(screenName);
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
		cards.add(winPanel, "winCard");

		cl = (CardLayout)(cards.getLayout());

		f.add(cards, BorderLayout.CENTER);
		f.setSize(WIDTH, HEIGHT);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}