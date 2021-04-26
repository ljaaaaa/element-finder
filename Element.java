import javax.swing.*;

public class Element extends Object{

	String imageName, fact;
	
	public Element(String imageName, int posX, int posY) {
		super (imageName, posX, posY);
		this.imageName = imageName;
		image = super.image;
		width = super.width;
		height = super.height;
		fact = "";
	}	

	//Moves an object a certain speed
	public void move(int speed) {
		posX += speed;
	}
	
	public void showFact(JPanel panel) {
		JLabel label1 = new JLabel();

	}
}