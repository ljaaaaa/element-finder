import java.awt.*;
import javax.swing.*;

public class Object {

	//Global Variables
	Image image;
	int posX, posY, width, height;

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
	public void move(int speed) {
		posX += speed;
	}

}