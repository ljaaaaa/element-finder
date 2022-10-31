import java.awt.Image;
import javax.swing.ImageIcon;

public class Item {
	//Global Variables
	public Image image;
	public int posX, posY, width, height;

	//Constructor
	public Item(String imageName, int posX, int posY) {
		ImageIcon icon = new ImageIcon(imageName);
		this.posX = posX;
		this.posY = posY;
		image = icon.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);	
	}	

	//Moves self a certain speed
	public void move(char pos, int speed) {
		if (pos == 'x') {
			posX += speed;
		}

		else if (pos == 'y') {
			posY += speed;
		}
	}
}
