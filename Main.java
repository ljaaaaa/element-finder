import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Main class where the game will be run from and played
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
	Container everything;

	//Main
	public static void main(String[] args) {
		Main m = new Main();
		m.setUpWindow(m);
	}

	//Constructor
	public Main() {
		kid = new Character();
		homePanel = new JPanel();
		gamePanel = new JPanel();
		winPanel = new JPanel();
		modules = new Modules();
		goingLeft = false;
		goingRight = true;
		dead = false;
		speed = 10;
		level = new Level(2);
		Container everything = new Container();

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

				if (level.levelNum == 2) {
					for (int x = 0; x < level.obstacles[0].lasers.size(); x++) {
						Obstacle tempArray = level.obstacles[0].lasers.get(x);
						tempArray.move(speed);
					}
				}

				for (int x=0; x < level.allObjects.length; x++) {
					Object tempObject = level.allObjects[x];
					tempObject.move(speed);
				} 
			}

			if (KeyEvent.getKeyText(event.getKeyCode()) == "Right") { //Moves screen left
				level.bg.move(-speed/4);
				goingRight = true;
				goingLeft = false;

				if (level.levelNum == 2) {
					for (int x = 0; x < level.obstacles[0].lasers.size(); x++) {
						Obstacle tempArray = level.obstacles[0].lasers.get(x);
						tempArray.move(-speed);
					}
				}

				for (int x=0; x < level.allObjects.length; x++) {
					Object tempObject = level.allObjects[x];
					tempObject.move(-speed);
				}
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

		//Shoots lasers here
		if (gamePanel.isVisible() && level.levelNum == 2) {
			for (int x = 0; x < level.obstacles.length; x++) {
				level.obstacles[x].shoot(-speed, 50);
			}
		}

		//Panels
		if (e.getSource() == start || e.getSource() == playAgain) { //Playing game
			cl.show(cards, "gameCard");
			gamePanel.requestFocusInWindow();
		}

		else if (e.getSource() == directions) { //Reading directions
			cl.show(cards, "directionsCard");
		}

		else if (e.getSource() == credits) { //Reading credits
			cl.show(cards, "creditsCard");
		}

		else if (level.elements.length == 0) { //Won level
			cl.show(cards, "winCard");

			level = new Level(1);
			kid = new Character();
		}

		//Buttons
		if (e.getSource() == home) { //Goes home
			cl.show(cards, "homeCard");
		}

		else if (e.getSource() == nextLevel) { //Next level
			cl.show(cards, "gameCard");
			level = new Level(level.levelNum + 1);
			gamePanel.requestFocusInWindow();
		}

		//Checks for element collisions
		for (int x=0; x < level.elements.length; x++) {
			Object tempElement = level.elements[x];

			if (gamePanel.isVisible() && modules.collided(kid, tempElement)){
				level.elements = modules.remove(level.elements, tempElement);
				level.refresh();
			}
		}	

		//Checks for obstacle collisions
		for (int x=0; x < level.obstacles.length; x++) {
			Object tempObstacle = level.obstacles[x];

			if (gamePanel.isVisible() && modules.collided(kid, tempObstacle)){
				dead = true;
				goingLeft = false;
				goingRight = false;
			}				
		}

		repaint();
		Toolkit.getDefaultToolkit().sync();
	}

	//Paint Component
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.drawImage(level.bg.image, 0, 0, 1200, 600, null);

		if (gamePanel.isVisible()) {
			//Background
			g2d.drawImage(level.bg.image, level.bg.posX, 0, null);
			g2d.drawImage(level.bg.image, level.bg.posX - level.bg.width, 0, null);

			//Lasers
			if (level.levelNum == 2) {
				for (int x = 0; x < level.obstacles[0].lasers.size(); x++) {
					Obstacle tempArray = level.obstacles[0].lasers.get(x);
					g2d.drawImage(tempArray.image, tempArray.posX, tempArray.posY, null); 
				}
			}

			//Elements and Obstacles
			for (int x=0; x < level.allObjects.length; x++) {
				Object tempObject = level.allObjects[x];
				g2d.drawImage(tempObject.image, tempObject.posX, tempObject.posY, null); }

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
		f.setSize(1200, 600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}