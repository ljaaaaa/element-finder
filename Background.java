import java.awt.*;
import javax.swing.*;

public class Background extends Object{
//Global Variables
	Image image;
	int width, height, posX, posY; 

//Constructor
	public Background(String imageName, int posX, int posY) {
		super(imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;
	}

	public void move(int speed) {
		if (posX >= width) {
			posX = 0;
		}

		else if (posX <= 0) {
			posX = width;
		}
		posX += speed;
	}
}