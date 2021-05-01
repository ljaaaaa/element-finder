import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel{
	
	String imageName;
	
	//Constructer
	public MyPanel(String imageName) {
		this.imageName = imageName;
	}
	
	//Paint Component
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		ImageIcon icon = new ImageIcon(imageName);
		Image image = icon.getImage();

		//Background
		g2d.drawImage(image, 0, 0, null);
	}
	
	public void addWinner(String fileName) {
		
	}
	
	public void showWinners() {
		
	}
}
