import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Element extends Object{
	public String imageName, fact;
	public double weight;
	public JButton back;

	public Element(String imageName, double weight, int posX, int posY) {
		super (imageName, posX, posY);
		this.imageName = imageName;
		image = super.image;
		width = super.width;
		height = super.height;
		fact = "";
		this.weight = weight;
		back = new JButton("Back");
	}	

	//Shows Info On Specific Element
	public void showFact(JPanel panel, ActionListener a) {
		JLabel name, image, atomicNum, atomicWeight, funFact;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		back.addActionListener(a);
		panel.add(back);

		//Atomic Number > 9
		if (imageName.substring(22, 23).equals("-")) {
			name = new JLabel(imageName.substring(23, imageName.length()-4));
			atomicNum = new JLabel("Atomic Number: " + imageName.substring(20, 22));
		}

		//Atomic Num <= 9
		else {
			name = new JLabel(imageName.substring(22, imageName.length()-4));
			atomicNum = new JLabel("Atomic Number: " + imageName.substring(20, 21));
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