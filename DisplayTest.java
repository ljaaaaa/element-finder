import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DisplayTest extends JPanel implements ActionListener{
	
	private int width;
	private int height;
	Background bg1;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	public static void main (String[] args) {
		DisplayTest t = new DisplayTest();
		t.setUpWindow(t);
	}
	
	public DisplayTest() {
		bg1 = new Background("src/backgrounds/SandyDesert.png", 0, 0);
		width = 100;
		height = 100;
		
		System.out.println("if this shows up a lot of times, that means this is recursive");
	}
	
	public void actionPerformed(ActionEvent e) {
		width += 100;
		height += 100;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(bg1.image, bg1.posX, 0, null);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0,  0, width, height);

	}
	
	public void setUpWindow(DisplayTest t) {
		JFrame f = new JFrame("test");
		
		JButton button = new JButton("expand");
		button.addActionListener(this);
		
		f.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		f.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		f.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		f.setResizable(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(t);
		f.add(button);
		System.out.println(f.getLayout());

		f.setVisible(true);		
	}
}