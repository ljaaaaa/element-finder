import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class SoundPlayer{

	Clip clip;
	File file;
	AudioInputStream audioStream;
	public SoundPlayer() {
		try {
			File file = new File("src/song.wav");
			AudioInputStream audioStream;
			audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			
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
	/*
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		Scanner scanner = new Scanner(System.in);
		
		File file = new File("src/song.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		
		String response = "";
			
		while(!response.equals("Q")) {
			System.out.println("P = play, S = Stop, R = Reset, Q = Quit");
			System.out.print("Enter your choice: ");
			
			response = scanner.next();
			response = response.toUpperCase();
			
			switch(response) {
				case ("P"): clip.start();
				break;
				case ("S"): clip.stop();
				break;
				case ("R"): clip.setMicrosecondPosition(0);
				break;
				case ("Q"): clip.close();
				break;
				default: System.out.println("Not a valid response");
			}
		 }
		System.out.println("Byeeee!");	
	}
	*/
}
