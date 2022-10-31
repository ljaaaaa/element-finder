import javax.swing.ImageIcon;

public class Kid extends Item {
	public int place, jumpCount;
	public boolean Jumping, left, right, dead;
	public ImageIcon[] picsLeft, picsRight;

	//Constructor
	public Kid(String imageName, int posX, int posY) {
		super(imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;

		jumpCount = 10;
		Jumping = false;
		
		right = true;
		left = false;
		dead = false;
		place = 0;
		
		picsLeft = new ImageIcon[] {new ImageIcon("src/images/KidL1.png"), new ImageIcon("src/images/KidL2.png"),
				new ImageIcon("src/images/KidL3.png"), new ImageIcon("src/images/KidL4.png") };
		
		picsRight = new ImageIcon[] {new ImageIcon("src/images/KidR1.png"), new ImageIcon("src/images/KidR2.png"),
				new ImageIcon("src/images/KidR3.png"), new ImageIcon("src/images/KidR4.png") };
	}

	//Makes the kid jump in a parabola
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