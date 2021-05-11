import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

//Home
		if (e.getSource() == bookHome || e.getSource() == creditsHome || e.getSource() == directionsHome 
				|| e.getSource() == winHome || e.getSource() == loseHome || e.getSource() == completedHome) {

			if (e.getSource() == directionsHome) {
				directionsPanel.imageNum = 0;
			}

			level = new Level(level.levelNum);
			kid = new Character("src/images/characterR1.png", 550, 200);
			cl.show(cards, "homeCard");
		}

		//Plays Game
		else if (e.getSource() == start || e.getSource() == winPlayAgain || e.getSource() == losePlayAgain) {
			level = new Level(level.levelNum);
			kid = new Character("src/images/characterR1.png", 550, 200);
			cl.show(cards, "gameCard");
			gamePanel.requestFocusInWindow();
		}		

		//Directions
		else if (e.getSource() == directions) {
			cl.show(cards, "directionsCard");
		}

		//Next Direction Slide
		else if (e.getSource() == nextDirection) {
			directionsPanel.repaint();
		}

		//Credits
		else if (e.getSource() == credits) {
			cl.show(cards, "creditsCard");		}

		//Sound
		else if (e.getSource() == soundControl) {
			if (soundControl.getText().equals("Sound Off")) {
				soundControl.setText("Sound On");
				sound.pause();
			}
			else {
				soundControl.setText("Sound Off");
				sound.play();
			}
		}

		//Next Level
		else if (e.getSource() == nextLevel) {
			if (level.levelNum <= 4) {
				level.levelNum ++;
				cl.show(cards, "gameCard");
				level = new Level(level.levelNum);
				gamePanel.requestFocusInWindow();	
			}
		}

		//Submit
		else if (e.getSource() == submit) {
			submit.removeActionListener(this);
			leaderboard.addWinner(name.getText(), clock.getTime(), clock.getMinutes(), clock.getSeconds());

			winner3.setText(leaderboard.viewWinners().get(2)); //Third
			winner1.setText(leaderboard.viewWinners().get(0)); //First
			winner2.setText(leaderboard.viewWinners().get(1)); //Second
		}

		//Elements Book
		else if (e.getSource() == elements) {
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
			
			case "Back":
				cl.show(cards, "bookCard");
			
			}
		}
		
