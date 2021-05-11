import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

//PROBLEM WITH MUSIC? AFTER SOME TIME IT STOPPED, AFTER PRESSING SOUND OFF BUTTON
public class Main extends JPanel implements ActionListener, KeyListener {
	//Global Variables
	Character kid;
	Modules modules;
	MyClock clock;
	Level level;
	Leaderboard leaderboard;
	SoundPlayer sound;
	Timer timer;

	JButton bookHome, creditsHome, directionsHome, winHome, loseHome, completedHome;
	JButton winPlayAgain, losePlayAgain, nextLevel, start, directions, credits, elements;
	JButton submit, soundControl, nextDirection, goToLeaderboard;
	JLabel time, winner1, winner2, winner3;
	JTextField name;

	JPanel cards, gamePanel;
	MyPanel homePanel, bookPanel, elementPanel, winPanel, losePanel, creditsPanel, completedPanel;
	DirectionsPanel directionsPanel;
	CardLayout cl;

	final int speed, WIDTH, HEIGHT;

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
		//something wrong here, with kid
		kid = new Character("src/images/characterR1.png", 550, 200);
		modules = new Modules();
		clock = new MyClock();
		level = new Level(1);
		leaderboard = new Leaderboard();
		sound = new SoundPlayer();
		sound.play();

		gamePanel = this;
		homePanel = new MyPanel("src/images/backgrounds/HomePicture.png");
		winPanel = new MyPanel("src/images/backgrounds/YouWin.png");
		losePanel = new MyPanel("src/images/backgrounds/YouLose.png");
		bookPanel = new MyPanel("src/images/backgrounds/TreasureChest.png");
		elementPanel = new MyPanel("src/images/backgrounds/TreasureChest.png");
		directionsPanel = new DirectionsPanel();
		creditsPanel = new MyPanel("");	
		completedPanel = new MyPanel("src/images/backgrounds/Completed.png");

		speed = 10; 
		WIDTH = 1200;
		HEIGHT = 600;

		collectedElements = new ArrayList <Element>();
		collectedNames = new ArrayList <String>();
		elementButtons = new ArrayList <JButton>();

		timer = new Timer(100, this);
	}

	//Key Listener - Listenes To Keys
	public void keyPressed(KeyEvent event) {
		if (!kid.dead) {
			if (KeyEvent.getKeyText(event.getKeyCode()) == "Space") { //Makes kid jump
				kid.Jumping = true;
			}

			if (KeyEvent.getKeyText(event.getKeyCode()) == "Left") { //Moves screen right
				if (kid.posX > level.objects[0].posX + level.objects[0].width) {
					kid.left = true;
					kid.right = false;
					level.moveAll(speed);
				}
			}

			if (KeyEvent.getKeyText(event.getKeyCode()) == "Right") { //Moves screen left
				if (kid.posX + kid.width < level.objects[1].posX) {
					kid.right = true;
					kid.left = false;
					level.moveAll(-speed);
				}
			}
		}
	}
	public void keyReleased(KeyEvent event) { }

	public void keyTyped(KeyEvent event) { }

	//Main Loop - Moves items on screen
	public void actionPerformed(ActionEvent e) {
		if (gamePanel.isVisible()) {
			//Runs timer
			if (clock.getSeconds() <= 9) {	
				time.setText(clock.getMinutes() + ":0" + clock.getSeconds());
			}

			else {
				time.setText(clock.getMinutes() + ":" + clock.getSeconds());
			}

			//Checks if kid is jumping
			if (kid.Jumping) {
				kid.Jump();
			}
		}

		else {
			clock.pauseTime = clock.pause();
		}

		//Won Game/ Leaderboard
		if ((level.levelNum == 5 && level.elements.length == 0)) {
			cl.show(cards, "completedCard");
			homePanel.add(goToLeaderboard);
		}

		//Won Level
		else if (level.elements.length == 0) {
			if (level.levelNum != 5) {
				level = new Level(level.levelNum);
				kid = new Character("src/images/characterR1.png", 550, 200);
				cl.show(cards, "winCard");
				winPanel.repaint();
			}
		}

		//Lose Level
		else if (kid.dead) {
			cl.show(cards, "loseCard");
			losePanel.repaint();
		}

		if (e.getActionCommand() != null) {

			switch (e.getActionCommand()) {
			case "Home":
				if (e.getSource() == directionsHome) {
					directionsPanel.imageNum = 0;
				}
				
				level = new Level(level.levelNum);
				kid = new Character("src/images/characterR1.png", 550, 200);
				cl.show(cards, "homeCard");
				break;

			case "Start": case "Play Again":
				level = new Level(level.levelNum);
				kid = new Character("src/images/characterR1.png", 550, 200);
				cl.show(cards, "gameCard");
				gamePanel.requestFocusInWindow();
				break;

			case "Directions":
				cl.show(cards, "directionsCard");
				break;

			case "Next":
				directionsPanel.repaint();
				break;

			case "Credits":
				cl.show(cards, "creditsCard");
				break;

			case "Back":
				cl.show(cards, "bookCard");
				break;
				
			case "Leaderboard":
				cl.show(cards, "completedCard");
				break;
				
			case "Sound On": case "Sound Off":
				if (soundControl.getText().equals("Sound Off")) {
					soundControl.setText("Sound On");
					sound.pause();
				}
				
				else {
					soundControl.setText("Sound Off");
					sound.play();
				}
				break;

			case "Next Level":
				if (level.levelNum <= 4) {
					level.levelNum ++;
					cl.show(cards, "gameCard");
					level = new Level(level.levelNum);
					gamePanel.requestFocusInWindow();	
				}
				break;

			case "Submit":
				submit.removeActionListener(this);
				leaderboard.addWinner(name.getText(), clock.getTime(), clock.getMinutes(), clock.getSeconds());

				winner3.setText(leaderboard.viewWinners().get(2)); //Third
				winner1.setText(leaderboard.viewWinners().get(0)); //First
				winner2.setText(leaderboard.viewWinners().get(1)); //Second	
				break;

			case "My Elements":
				cl.show(cards, "bookCard");		
				bookPanel.removeAll();
				elementButtons.clear();

				bookHome = new JButton("Home");
				bookHome.addActionListener(this);
				bookPanel.add(bookHome);

				//Add spaces for first row
				for (int x = 0; x < 6; x++) {
					bookPanel.add(new JLabel(""));
				}

				//Add Elements To Screen
				for (int x = 0; x < collectedElements.size(); x++) {
					ImageIcon tempIcon = new ImageIcon(collectedNames.get(x));
					elementButtons.add(new JButton(tempIcon));
					elementButtons.get(x).addActionListener(this);
					bookPanel.add(elementButtons.get(x));
				}

				//Adds Empty Space (GridLayout)
				for (int x = 0; x < (28 - collectedElements.size()); x++) {
					bookPanel.add(new JLabel(""));
				}
				break;
			}
		}

		//Element Facts
		for (int x = 0; x < elementButtons.size(); x++) {
			if (e.getSource() == elementButtons.get(x)) {
				cl.show(cards, "elementCard");
				elementPanel.removeAll();
				collectedElements.get(x).showFact(elementPanel, this);
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
		for (int x=0; x < level.obstacles.length; x++) {
			if (modules.collided(kid, level.obstacles[x], new Element("", 0, 0, 0))){
				kid.dead = true;
			}				
		}

		//Checks for other obstacle collisions
		for (int x = 0; x < level.obstacles2.length; x++) {
			if (modules.collided(kid, level.obstacles2[x], new Element("", 0, 0, 0))){
				kid.dead = true;
			}	
		}

		//Moves Objects
		if (gamePanel.isVisible()) {
			for (int x = 0; x < level.obstacles.length; x++) {
				switch (level.levelNum) {
				
				case 1:
					break;

				case 2:
					if (modules.obstacleOnScreen(level.obstacles[x], WIDTH, HEIGHT)) {
						level.obstacles[x].shoot(-speed, 'l');	
					}

					else {
						level.obstacles[x].projectiles.clear();
					}

					for (int y = 0; y < level.obstacles[x].projectiles.size(); y++) {
						if (modules.collided(kid, level.obstacles[x].projectiles.get(y), new Element("", 0, 0, 0))){
							kid.dead = true;
						}
					}
					break;

				case 3:
					if (modules.obstacleOnScreen(level.obstacles[x], WIDTH, HEIGHT)) {
						level.obstacles[x].fly(-speed);
					}
					break;

				case 4:
					if (modules.obstacleOnScreen(level.obstacles[x], WIDTH, HEIGHT)) {
						level.obstacles[x].shoot(speed, 's');	
					}

					else {
						level.obstacles[x].projectiles.clear();
					}

					for (int y = 0; y < level.obstacles[x].projectiles.size(); y++) {
						if (modules.collided(kid, level.obstacles[x].projectiles.get(y), new Element("", 0, 0, 0))){
							kid.dead = true;
						}
					}
					break;

				case 5:
					if (modules.obstacleOnScreen(level.obstacles[x], WIDTH, HEIGHT)) {
						level.obstacles[x].gloop();
					}

					if (modules.collided(kid, level.obstacles[x].lava, new Element("", 0, 0, 0))){
						kid.dead = true;
					}

					break;
				}
			}
		}

		//Repaints Screen
		repaint();
		Toolkit.getDefaultToolkit().sync();
	}

	//Paint Component - paints on screen
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

		//Obstacles
		for (int x = 0; x < level.obstacles.length; x++) {
			Obstacle tempObstacle = level.obstacles[x];

			//Lasers/ Snowballs
			if (level.levelNum == 2 || level.levelNum == 4) {
				for (int y = 0; y < level.obstacles[x].projectiles.size(); y++) {
					Obstacle tempArray = level.obstacles[x].projectiles.get(y);
					g2d.drawImage(tempArray.image, tempArray.posX, tempArray.posY, null); 
				}					
			}

			//Lava
			if (level.levelNum == 5) {
				g2d.drawImage(tempObstacle.lava.image, tempObstacle.lava.posX, tempObstacle.lava.posY, null); 
			}

			g2d.drawImage(tempObstacle.image, tempObstacle.posX, tempObstacle.posY, null); 
		}

		//Extra obstacles
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
		kid.Animate();
		g2d.drawImage(kid.image, kid.posX, kid.posY, null);
	}

	//Sets Up Window	
	public void setUpWindow() {
		JFrame f = new JFrame("Element Finder");

		//Credits Panel
		creditsPanel.setLayout(null);

		creditsHome = modules.setUpButton(this, "Home", 1115, 10, 75, 25);
		creditsPanel.add(creditsHome);

		ArrayList<String> text = modules.readFile("src/Credits.txt");
		ArrayList<JLabel> creditsLabels = new ArrayList<JLabel>();

		for (int x = 0; x < text.size(); x++) {
			creditsLabels.add(new JLabel(text.get(x)));
			creditsLabels.get(x).setBounds(10, x*20+10, 1200, 20);
			creditsPanel.add(creditsLabels.get(x));
		}

		//Directions Panel
		directionsPanel.setLayout(null);

		directionsHome = modules.setUpButton(this, "Home", 1115, 10, 75, 25);
		directionsPanel.add(directionsHome);

		nextDirection = modules.setUpButton(this, "Next", 1030, 10, 75, 25);
		directionsPanel.add(nextDirection);

		//Home Panel		
		homePanel.setLayout(null);

		soundControl = modules.setUpButton(this, "Sound Off", 1080, 10, 110, 35);
		homePanel.add(soundControl);

		start = modules.setUpButton(this, "Start", 500, 120, 200, 75);
		homePanel.add(start);

		directions = modules.setUpButton(this, "Directions", 350, 220, 150, 50);
		homePanel.add(directions);

		credits = modules.setUpButton(this, "Credits", 525, 220, 150, 50);
		homePanel.add(credits);

		elements = modules.setUpButton(this, "My Elements", 700, 220, 150, 50);
		homePanel.add(elements);

		goToLeaderboard = modules.setUpButton(this, "Leaderboard", 525, 290, 150, 50);

		//Game Panel
		time = new JLabel(clock.getTime() + "0");	
		gamePanel.add(time);

		gamePanel.addKeyListener(this);
		gamePanel.setFocusable(true);

		//Win Panel	
		winPanel.setLayout(null);

		winHome = modules.setUpButton(this, "Home", 350, 150, 150, 50);
		winPanel.add(winHome);

		winPlayAgain = modules.setUpButton(this, "Play Again", 525, 150, 150, 50);
		winPanel.add(winPlayAgain);		

		nextLevel = modules.setUpButton(this, "Next Level", 700, 150, 150, 50);
		winPanel.add(nextLevel);

		//Lose Panel
		losePanel.setLayout(null);

		loseHome = modules.setUpButton(this, "Home", 425, 150, 150, 50);
		losePanel.add(loseHome);

		losePlayAgain = modules.setUpButton(this, "Play Again", 625, 150, 150, 50);
		losePanel.add(losePlayAgain);

		//Book Panel
		bookPanel.setLayout(new GridLayout(5, 7, 3, 3));

		//Completed Panel
		completedPanel.setLayout(null);

		completedHome = modules.setUpButton(this, "Home", 10, 10, 75, 25);
		completedPanel.add(completedHome);

		name = new JTextField("Your Name!");
		name.setBounds(450, 255, 180, 30);
		completedPanel.add(name);

		submit = modules.setUpButton(this, "Submit", 640, 250, 100, 40);
		completedPanel.add(submit);

		winner3 = new JLabel(leaderboard.viewWinners().get(2)); //Third
		winner3.setBounds(360, 435, 150, 50);
		completedPanel.add(winner3);

		winner1 = new JLabel(leaderboard.viewWinners().get(0)); //First
		winner1.setBounds(535, 335, 150, 50);
		completedPanel.add(winner1);

		winner2 = new JLabel(leaderboard.viewWinners().get(1)); //Second
		winner2.setBounds(705, 385, 150, 50);
		completedPanel.add(winner2);

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
		cards.add(completedPanel, "completedCard");
		cl = (CardLayout)(cards.getLayout());

		timer.start();			

		f.add(cards, BorderLayout.CENTER);
		f.setSize(WIDTH, HEIGHT);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}