public class MyClock{

	long startTime, minutes, seconds;

	public MyClock() {
		startTime = System.currentTimeMillis();
		minutes = (System.currentTimeMillis() - startTime) /1000/ 60;
		seconds = (System.currentTimeMillis() - startTime) /1000 % 60;
	}

	public String getTime() {		
		minutes = (System.currentTimeMillis() - startTime) /1000/ 60;
		seconds = (System.currentTimeMillis() - startTime) /1000 % 60;

		if (seconds <= 9) {
			return minutes + ":0" + seconds; 
		}

		else {
			return minutes + ":" + seconds;	
		}
	}
}