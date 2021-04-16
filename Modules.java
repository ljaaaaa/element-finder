public class Modules {

//Checks if character has collided with an object
	public boolean collided(Character c, Object e) {	
		int cLeft = c.posX;
		int cRight = c.posX + c.width;
		int cTop = c.posY;
		int cBottom = c.posY + c.height;

		int eLeft = e.posX;
		int eRight = e.posX + e.width;
		int eTop = e.posY;
		int eBottom = e.posY + e.height;
		
		if(((cLeft >= eLeft && cLeft <= eRight) || 
				(cRight >= eLeft && cRight <= eRight) ||
				(cLeft >= eLeft && cRight <= eRight) || 
				(cLeft <= eLeft && cRight >= eRight)) &&

				((cTop >= eTop && cTop <= eBottom) || 
						(cBottom <= eBottom && cBottom >= eTop) ||
						(cTop >= eBottom && cBottom <= eBottom ) ||
						(cTop <= eBottom && cBottom >= eBottom))) {

			return true;
		}
		return false;
	}
//Removes an item from an array of the object class	
	public Object[] remove(Object[] array, Object item) {
		Object[] tempElements = new Object[array.length-1];

		//put item last in list
		for (int x=0; x < array.length; x++) {

			if (array[x] == item) {
				Object temp = array[0];
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