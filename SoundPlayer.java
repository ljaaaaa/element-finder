import java.io.*;
import javax.sound.sampled.*;

public class SoundPlayer{
	Clip clip;
	AudioInputStream audioStream;
	
	public SoundPlayer() {
		try {
			File file = new File("src/song.wav");
			audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}	
	}
	
	public void play() {
		 clip.start();
	}
	
	public void pause() {
		 clip.stop();
	}
	
	public void reset() {
		 clip.setMicrosecondPosition(0);
	}
}
