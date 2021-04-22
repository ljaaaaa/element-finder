public class Element extends Object{

	public Element(String imageName, int posX, int posY) {
		super (imageName, posX, posY);
		image = super.image;
		width = super.width;
		height = super.height;

	}	

	//Moves an object a certain speed
	public void move(int speed) {
		posX += speed;
	}
}