package fr.isis.hasp.agentniveausonore;

import java.util.Date;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

import fr.isis.hasp.ivycommunication.IvyCommunication;
import fr.isis.hasp.ivycommunication.IvyCommunicationInterface;
import fr.isis.hasp.objetsmetier.Constantes;
import fr.isis.hasp.objetsmetier.Message;

/**
 * Cette classe affiche les variations sonores que capte le micro, si le micro ne capte rien -> 0
 * 
 * @author Arnaud
 */

public class DbMeter extends Thread{

	private static final double SEUIL = 1;

	final IvyCommunicationInterface ivy = IvyCommunication.getIvyCommunicationProxy("AgentCaptureSon");
	
	byte[] b = new byte[70000];
	private long temporisation = 5000L;

	private AudioFormat audioFormat;
	private TargetDataLine targetDataLine;
	private int calibration = 0;// pour la calibration du micro, mais pas super important pour nous etant donn�e que l'on capte juste les variations

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DbMeter().run();
	}

	public void run() {
		System.out.println("Hello DbMeter");
		while (!interrupted()) {
			try {
				for (int i = 0; i < b.length; i++)
					b[i] = 0;
				captureAudio();
				Thread.sleep(temporisation, 0);
				
				double level = calculateRMSLevel(b);
				
//				System.out.println("Level : "+ (calculateRMSLevel(b) + calibration));//Affiche le volume sonore RMS (correspond � peu pret au volume db
				
				Message message = new Message();
				message.setCategorieMessage(Constantes.CAPTEUR_SON);
				message.setDateMessage(new Date());
				message.setNumeroCapteur(new Integer(0));
				
				System.out.print("LEVEL : "+level);
				
				if(level>SEUIL){
					message.setMessage("1");
					ivy.postMessage(message);
				}else{
					message.setMessage("0");
					ivy.postMessage(message);
				}
				
				targetDataLine.stop();
				targetDataLine.close();
			} catch (InterruptedException e1) {
				return;
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
			System.out.println("Vérifier qu'un micro est branché çà ce PC.\n");
		
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
