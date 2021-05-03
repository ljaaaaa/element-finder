public class MyClock{
	long startTime, minutes, seconds;
	double time;

	public MyClock() {
		startTime = System.currentTimeMillis();
		minutes = (System.currentTimeMillis() - startTime) /1000/ 60;
		seconds = (System.currentTimeMillis() - startTime) /1000 % 60;
	}

	public double getTime() {
		minutes = (System.currentTimeMillis() - startTime) /1000/ 60;
		seconds = (System.currentTimeMillis() - startTime) /1000 % 60;		
		
		return (int)minutes + (double)seconds/100;
	}
	
	public int getMinutes() {
		minutes = (System.currentTimeMillis() - startTime) /1000/ 60;
		
		return (int)minutes;
	}
	
	public double getSeconds() {
		seconds = (System.currentTimeMillis() - startTime) /1000 % 60;		

		return (double)seconds/100;
	}
}