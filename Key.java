import java.awt.event.*;

//This class is used so multiple keys can be pressed at the same time
public class Key implements KeyListener{
	
	boolean isPressed;
	int key;
	
	public Key (int key) {
		isPressed = false;
		this.key = key;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == key) {
			isPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == key) {
			isPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		
	}
}
