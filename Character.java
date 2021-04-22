import javax.swing.*;

public class Character extends Object {

	//Global Variables
	int place, jumpCount;
	boolean Jumping;
	ImageIcon[] picsLeft, picsRight;
	ImageIcon picDeadL, picDeadR;

	//Constructor
	public Character(String imageName, int posX, int posY) {
		super(imageName, posX, posY);
		String charName;
		image = super.image;
		width = super.width;
		height = super.height;

		jumpCount = 10;
		Jumping = false;
		picsLeft = new ImageIcon[4];
		picsRight = new ImageIcon[4];
		place = 0;
		
		//Sets left pictures
		for (int x=0; x < picsLeft.length; x++) {
			charName = "src/characterL" + (x+1) + ".png";
			picsLeft[x] = new ImageIcon(charName);
		}

		//Sets right pictures
		for (int x=0; x < picsRight.length; x++) {
			charName = "src/characterR" + (x+1) + ".png";
			picsRight[x] = new ImageIcon(charName);
		}
		//Sets dead picture
		picDeadL = new ImageIcon("src/CharacterLdead.png");
		picDeadR = new ImageIcon("src/CharacterRdead.png");
	}

	//This function makes the character jump in a parabola
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

	public void Animate(String direction) {
		if (direction == "left") {
			image = picsLeft[place].getImage();
		}
		else if (direction == "right") {
			image = picsRight[place].getImage();
		}

		else if (direction == "dead") {
			if (image == picsLeft[place].getImage()) {
				image = picDeadL.getImage();
			}

			else if (image == picsRight[place].getImage()) {
				image = picDeadR.getImage();
			}
		}

		place++;
		if (place > 3){
			place = 0;
		}
	}
}