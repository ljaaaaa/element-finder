public class Modules {

//Checks if character has collided with an object
	public boolean collided(Character c, Obstacle o, Element e) {
	
		int oLeft = o.posX; //Checks collision with character and object
		int oRight = o.posX + o.width;
		int oTop = o.posY;
		int oBottom = o.posY + o.height;
		
		if (o == null) { //If Obstacle is null, then checks collision with character and element
			System.out.println("not dead!!! (ha ha eclipse)");
			oLeft = e.posX;
			oRight = e.posX + o.width;
			oTop = e.posY;
			oBottom = e.posY + o.height;
		}
		
		int cLeft = c.posX;
		int cRight = c.posX + c.width;
		int cTop = c.posY;
		int cBottom = c.posY + c.height;

		if(((cLeft >= oLeft && cLeft <= oRight) || 
				(cRight >= oLeft && cRight <= oRight) ||
				(cLeft >= oLeft && cRight <= oRight) || 
				(cLeft <= oLeft && cRight >= oRight)) &&

				((cTop >= oTop && cTop <= oBottom) || 
						(cBottom <= oBottom && cBottom >= oTop) ||
						(cTop >= oBottom && cBottom <= oBottom ) ||
						(cTop <= oBottom && cBottom >= oBottom))) {
			
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
}