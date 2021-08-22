public class MyClock{
	public long startTime, pauseStartTime; //The time (milliseconds) it starts;
	public long minutes, seconds; //Minutes and seconds passed
	public long pauseTime; //Amount of time paused
	public boolean paused; //If game is paused

	public MyClock() {
		startTime = System.currentTimeMillis();
		pauseStartTime = 0;
		pauseTime = 0;
		paused = false;
		minutes = (System.currentTimeMillis() - startTime) /1000/ 60;
		seconds = (System.currentTimeMillis() - startTime) /1000 % 60;
	}

	public double getTime() {
		minutes = getMinutes();
		seconds = getSeconds();
			
		double time = (double)minutes + (double)seconds/100;
	
		return Math.round(time*100.0)/100.0;
	}

		
	public int getMinutes() {
		if (paused) {
			pauseTime += System.currentTimeMillis() - pauseStartTime;
			paused = false;
			pauseStartTime = 0;
		}
		minutes = (System.currentTimeMillis() - startTime - pauseTime) /1000 / 60;

		return (int)minutes;
	}

	public int getSeconds() {
		seconds = (System.currentTimeMillis() - startTime) /1000 % 60;

		if (paused) {
			pauseTime += System.currentTimeMillis() - pauseStartTime;
			paused = false;
			pauseStartTime = 0;
		}
		seconds = (System.currentTimeMillis() - startTime - pauseTime) /1000 % 60;
		
		return (int)seconds;
	}

	public long pause() {
		if (paused == false) {
			pauseStartTime = System.currentTimeMillis();	
			paused = true;
		}
		return pauseTime;
	}
}