import java.awt.*;
import javax.swing.*;

public class Object {
	//Global Variables
	public Image image;
	public int posX, posY, width, height;

	//Constructor
	public Object(String imageName, int posX, int posY) {
		ImageIcon icon = new ImageIcon(imageName);
		this.posX = posX;
		this.posY = posY;
		image = icon.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);	
	}	

	//Moves an object a certain speed
	public void move(char pos, int speed) {
		if (pos == 'x') {
			posX += speed;
		}

		else if (pos == 'y') {
			posY += speed;
		}
	}
}
