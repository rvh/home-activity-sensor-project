package fr.isis.hasp.agentnombrepersonne;

import java.util.Date;

import fr.isis.hasp.ivycommunication.IvyCommunication;
import fr.isis.hasp.ivycommunication.IvyCommunicationInterface;
import fr.isis.hasp.objetsmetier.Constantes;
import fr.isis.hasp.objetsmetier.Message;

public class Thread_Capteur_CO2 extends Thread {
	
	private Integer nombrePers = 0;
	private int frequence = 1000;
	
	final IvyCommunicationInterface ivy = IvyCommunication
			.getIvyCommunicationProxy("AgentNombrePersonne");
	
	public Thread_Capteur_CO2(Integer nombrePers, int frequence){
		this.nombrePers = nombrePers;
		this.frequence = frequence;
	}
	
	public void run(){
		
		Message message = new Message();
		message.setCategorieMessage(Constantes.CAPTEUR_CO2);
		
		while (!interrupted()){
			message.setDateMessage(new Date());
			message.setNumeroCapteur(0);
			message.setMessage(nombrePers+"");
			ivy.postMessage(message);
			System.out.println(message);
			try {
				Thread.sleep(frequence);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	public void setNombrePersonne(Integer nombrePers){
		this.nombrePers = nombrePers;
	}
	
	public void setFrequence(int frequence){
		this.frequence = frequence*1000;
	}
}
