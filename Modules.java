public class Modules {

	//Removes an element from element array
	public Element[] removeElement(Element[] array, Element item) {
		Element[] newArray = new Element[array.length-1];
		int pos = 0;

		for (int x = 0; x < newArray.length; x++) {
			if (array[pos] != item) {
				newArray[x] = array[pos];
			}
			else {
				newArray[x] = array[pos+1];
				pos++;
			}
			pos++;
		}

		return newArray;
	}

	//Checks if obstacle is on screen
	public boolean obstacleOnScreen(Obstacle o, int screenWidth, int screenHeight) {
		if (o.posX <= screenWidth && o.posX + o.width >= 0 &&
				o.posY <= screenHeight && o.posY + o.height >= 0 ) {
			return true;
		}
		return false;		
	}

	//Checks if character has collided with an object
	public boolean collided(Character c, Obstacle o, Element e) {

		int cLeft = c.posX;
		int cRight = c.posX + c.width;
		int cTop = c.posY;
		int cBottom = c.posY + c.height;

		int tLeft = 0, tRight = 0, tTop = 0, tBottom = 0;

		if (o.width == -1) { //checks collision with character and element
			tLeft = e.posX;
			tRight = e.posX + e.width;
			tTop = e.posY;
			tBottom = e.posY + e.height;
		}

		else if (e.width == -1){ //checks collision with character and object
			tLeft = o.posX; 
			tRight = o.posX + o.width;
			tTop = o.posY;
			tBottom = o.posY + o.height;
		}

		if(((cLeft >= tLeft && cLeft <= tRight) ||  //Left & right collisions
				(cRight >= tLeft && cRight <= tRight) ||
				(cLeft >= tLeft && cRight <= tRight) || 
				(cLeft <= tLeft && cRight >= tRight)) &&

				((cTop >= tTop && cTop <= tBottom) ||  //Top & bottom collisions
						(cBottom <= tBottom && cBottom >= tTop) ||
						(cTop >= tBottom && cBottom <= tBottom ) ||
						(cTop <= tBottom && cBottom >= tBottom))) {
			return true;
		}
		return false;
	}
}