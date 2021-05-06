import javax.swing.*;

public class Character extends Object {

	//Global Variables
	int place, jumpCount;
	boolean Jumping, left, right, dead;
	ImageIcon[] picsLeft, picsRight;

	//Constructor
	public Character(String imageName, int posX, int posY) {
		super(imageName, posX, posY);
		String charName;
		image = super.image;
		width = super.width;
		height = super.height;
		jumpCount = 10;
		Jumping = false;
		
		right = true;
		left = false;
		dead = false;
		place = 0;
		
		picsLeft = new ImageIcon[] {new ImageIcon("src/characterL1.png"), new ImageIcon("src/characterL2.png"),
				new ImageIcon("src/characterL3.png"), new ImageIcon("src/characterL4.png") };
		
		picsRight = new ImageIcon[] {new ImageIcon("src/characterR1.png"), new ImageIcon("src/characterR2.png"),
				new ImageIcon("src/characterR3.png"), new ImageIcon("src/characterR4.png") };
	}

	//Makes the character jump in a parabola
	public void Jump() {
		int neg;

		if (Jumping) {
			if (jumpCount >= -10){
				neg = 1;
				if (jumpCount < 0){
					neg = -1;
				}
				posY -= (jumpCount * jumpCount) * 0.5 * neg;
				jumpCount -= 1;
			}

			else {
				posY = 200;
				jumpCount = 10;
				Jumping = false;
			}
		}
	}

	//Animates kid/rotates images
	public void Animate() {
		if (right) {
			image = picsRight[place].getImage();
		}
		
		else if (left ) {
			image = picsLeft[place].getImage();
		}

		place++;
		if (place > 3){
			place = 0;
		}
	}
}