import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel{

	String imageName;
	String[] imageNames;
	int imageNum;

	//For Nomal My Panel
	public MyPanel(String imageName) {
		this.imageName = imageName;
		this.imageNames = new String[0];
	}

	//For Directions Panel
	public MyPanel(String[] imageNames) {
		imageNum = 0;

		//Makes imageNames the given array
		if (imageNames.length > 0) {
			this.imageNames = imageNames; 
			this.imageName = this.imageNames[0];
		}
		
		//If array is empty, makes default array
		else {
			this.imageNames = new String[] {"src/images/1-Navigation.png", "src/images/2-Controls.png", "src/images/3-Obstacles.png", 
					"src/images/4-Elements.png", "src/images/5-Challenge.png"};
			this.imageName = this.imageNames[0];
		}
	}

	//Paint Component
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		if (imageNames.length == 0) {
			ImageIcon icon = new ImageIcon(imageName);
			Image image = icon.getImage();

			//Background
			g2d.drawImage(image, 0, 0, null);
		}

		else {
			ImageIcon icon = new ImageIcon(imageNames[imageNum]);
			Image image = icon.getImage();

			imageNum++;
			if (imageNum > imageNames.length-1){
				imageNum = 0;
			}

			g2d.drawImage(image, 0, 0, null);
		}
	}
}
