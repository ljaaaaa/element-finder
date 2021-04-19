public class Modules {

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

		if(((cLeft >= tLeft && cLeft <= tRight) || 
				(cRight >= tLeft && cRight <= tRight) ||
				(cLeft >= tLeft && cRight <= tRight) || 
				(cLeft <= tLeft && cRight >= tRight)) &&

				((cTop >= tTop && cTop <= tBottom) || 
						(cBottom <= tBottom && cBottom >= tTop) ||
						(cTop >= tBottom && cBottom <= tBottom ) ||
						(cTop <= tBottom && cBottom >= tBottom))) {
			return true;
		}
		return false;
	}

	public Element[] removeElement(Element[] array, Element item) {

		Element[] tempElements = new Element[array.length-1];

		//put item last in list
		for (int x=0; x < array.length; x++) {

			if (array[x] == item) {
				Element temp = array[0];
				array[0] = array[x];
				array[x] = temp;
			}
		}

		//removes item (which is the last item in the list)
		for (int x=0; x < array.length-1; x++) {
			array[x] = array[x+1];
		}

		for (int x=0; x<array.length-1; x++) {
			tempElements[x] = array[x];
		}
		//array is changed to new array
		array = tempElements;

		return array;
	}
	
	public boolean onScreen(Element e, int screenWidth, int screenHeight) { //look into this?
		if (e.posX <= screenWidth && e.posX >= 0 &&
				e.posY <= screenHeight && e.posY >= 0 ) {
			return true;
		}
		return false;		
	}
	
	public boolean onScreen(Obstacle o, int screenWidth, int screenHeight) { //look into this?
		if (o.posX <= screenWidth && o.posX >= 0 &&
				o.posY <= screenHeight && o.posY >= 0 ) {
			return true;
		}
		return false;		
	}
}