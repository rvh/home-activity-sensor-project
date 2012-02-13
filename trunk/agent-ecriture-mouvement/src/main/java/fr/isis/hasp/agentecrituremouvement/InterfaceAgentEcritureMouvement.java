package fr.isis.hasp.agentecrituremouvement;

import fr.isis.hasp.ivycommunication.IvyCommunication;
import fr.isis.hasp.ivycommunication.IvyCommunicationInterface;
import fr.isis.hasp.objetsmetier.Constantes;

public class InterfaceAgentEcritureMouvement {

	IvyCommunicationInterface ivy = IvyCommunication.getIvyCommunicationProxy(Constantes.CAPTEUR_MOUVEMENT);

	
	private PortSerie threadAgentEcritureMouvement = null;
	
	public InterfaceAgentEcritureMouvement(){
	}
	
	public void start(){
		threadAgentEcritureMouvement = PortSerie.getInstance(ivy);
		threadAgentEcritureMouvement.init("COM1");
		System.out.println("Start");
	}
	
	public void stop(){
		threadAgentEcritureMouvement.end();
		threadAgentEcritureMouvement = null;
		System.out.println("Stop");
	}
}
