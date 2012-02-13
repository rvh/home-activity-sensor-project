package fr.isis.hasp.agentecrituremouvement;

import fr.isis.hasp.ivycommunication.IvyCommunication;
import fr.isis.hasp.ivycommunication.IvyCommunicationInterface;
import fr.isis.hasp.objetsmetier.Constantes;

public class Main extends Thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	@Override
	public void run() {
		IvyCommunicationInterface ivy = IvyCommunication
				.getIvyCommunicationProxy(Constantes.CAPTEUR_MOUVEMENT);

		PortSerie port = PortSerie.getInstance(ivy);

		port.init("COM1");
		port.run();
		
		while(!interrupted()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
