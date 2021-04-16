import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.event.*;

public class Sound implements ActionListener{

	public Mixer mixer;
	public Clip clip;
	public Timer t = new Timer(50, this);

	public static void main(String[] args) {
		Sound s = new Sound();
		s.playMusic();
}

	public void actionPerformed(ActionEvent e) {
		
	}

	public void playMusic()
{
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();

		mixer = AudioSystem.getMixer(mixInfos[1]);

		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);


		try {
			clip = (Clip)mixer.getLine(dataInfo);

		}

		catch (LineUnavailableException lue) {
			lue.printStackTrace();

		}

		try {

			URL soundURL = Sound.class.getResource("song.wav");
			System.out.println(soundURL);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
			clip.open(audioStream);
		}

		catch (LineUnavailableException lue) {
			lue.printStackTrace();
		}
		catch (UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		clip.start();

		do {

			try {
				System.out.println("hi");
				Thread.sleep(50);
			}
			catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		} while (clip.isActive());

		if (clip.isActive()) {
			System.out.println("active");
		}


		else if (!clip.isActive()) {
			System.out.println("nop");
		}

}
}
