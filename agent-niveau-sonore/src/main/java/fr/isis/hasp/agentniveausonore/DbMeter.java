package fr.isis.hasp.agentniveausonore;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/**
 * Cette classe affiche les variations sonores que capte le micro, si le micro ne capte rien -> 0
 * 
 * @author Arnaud
 */

public class DbMeter {

	byte[] b = new byte[14000];

	private AudioFormat audioFormat;
	private TargetDataLine targetDataLine;
	private int calibration = 0;// pour la calibration du micro, mais pas super important pour nous etant donnée que l'on capte juste les variations

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DbMeter();

	}

	public DbMeter() {
		while (true) {
			try {
				for (int i = 0; i < b.length; i++)
					b[i] = 0;
				captureAudio();
				Thread.sleep(1000, 0);
				
				System.out.println("Level : "+ (calculateRMSLevel(b) + calibration));//Affiche le volume sonore RMS (correspond à peu pret au volume db
				
				targetDataLine.stop();
				targetDataLine.close();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void captureAudio() {
		try {
			// Get things set up for capture
			audioFormat = getAudioFormat();
			DataLine.Info dataLineInfo = new DataLine.Info(
					TargetDataLine.class, audioFormat);
			targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);

			// Create a thread to capture the microphone
			// data into an audio file and start the
			// thread running. It will run until the
			// Stop button is clicked. This method
			// will return after starting the thread.
			new CaptureThread().start();
		} catch (Exception e) {
			System.out.println("Vérifier qu'un micro est branché à ce PC.");
		
			e.printStackTrace();
			System.exit(0);
		}// end catch
	}

	private AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F;
		// 8000,11025,16000,22050,44100
		int sampleSizeInBits = 8;//on capture en 8 bits pour pouvoir coller ensuite au tableau de bit (faciliter le calcul)
		// 8,16
		int channels = 1;
		// 1,2
		boolean signed = true;
		// true,false
		boolean bigEndian = false;
		// true,false
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
				bigEndian);
	}

	private double calculateRMSLevel(byte[] audioData) {
		long lSum = 0;
		for (int i = 0; i < audioData.length; i++) {
	            lSum = lSum + audioData[i];
		}

		double dAvg = lSum / audioData.length;

		double sumMeanSquare = 0d;
		for (int j = 0; j < audioData.length; j++)
			sumMeanSquare = sumMeanSquare + Math.pow(audioData[j] - dAvg, 2d);

		double averageMeanSquare = sumMeanSquare / audioData.length;
		return (Math.pow(averageMeanSquare, 0.5d));
	}

	class CaptureThread extends Thread {
		public void run() {

			try {
				targetDataLine.open(audioFormat);
				targetDataLine.start();

				AudioInputStream tmp = new AudioInputStream(targetDataLine);
				tmp.read(b);

			} catch (Exception e) {
				e.printStackTrace();
			}// end catch

		}// end run
	}

}
