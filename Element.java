import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

public class Element extends Object{
	String imageName, fact;
	double weight;
	JButton back;

	public Element(String imageName, double weight, int posX, int posY) {
		super (imageName, posX, posY);
		this.imageName = imageName;
		image = super.image;
		width = super.width;
		height = super.height;
		fact = "Coming Soon!";
		this.weight = weight;
		back = new JButton("Back");
	}	

	//Moves an object a certain speed
	public void move(int speed) {
		posX += speed;
	}

	//Shows Info On Specific Element
	public void showFact(JPanel panel, ActionListener a) {
		JLabel name, image, atomicNum, atomicWeight, funFact;

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		back.addActionListener(a);
		panel.add(back);

		//Atomic Number > 9
		if (imageName.substring(15, 16).equals("-")) {
			name = new JLabel(imageName.substring(16, imageName.length()-4));
			atomicNum = new JLabel("Atomic Number: " + imageName.substring(13, 15));
		}

		//Atomic Num <= 9
		else {
			name = new JLabel(imageName.substring(15, imageName.length()-4));
			atomicNum = new JLabel("Atomic Number: " + imageName.substring(13, 14));
		}

		name.setBorder(new EmptyBorder(15, 15, 15, 15));		
		panel.add(name);

		ImageIcon imageIcon = new ImageIcon(imageName);
		image = new JLabel(imageIcon);
		image.setBorder(new EmptyBorder(15, 15, 15, 15));		
		panel.add(image);

		atomicNum.setBorder(new EmptyBorder(15, 15, 15, 15));		
		panel.add(atomicNum);

		atomicWeight = new JLabel("Atomic Weight: " + weight);
		atomicWeight.setBorder(new EmptyBorder(15, 15, 15, 15));		
		panel.add(atomicWeight);

		funFact = new JLabel("Fun Fact: " + fact);
		funFact.setBorder(new EmptyBorder(15, 15, 15, 15));		
		panel.add(funFact);
	}
}