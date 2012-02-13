package fr.isis.hasp.agentanalysemouvement;

public class InterfaceAgentEcritureMouvement {

	private Main threadAgentEcritureMouvement = null;
	
	public InterfaceAgentEcritureMouvement(){
	}
	
	public void start(){
		threadAgentEcritureMouvement = new Main();
		threadAgentEcritureMouvement.start();
		System.out.println("Start");
	}
	
	public void stop(){
		threadAgentEcritureMouvement.interrupt();
		threadAgentEcritureMouvement = null;
		System.out.println("Stop");
	}
}
