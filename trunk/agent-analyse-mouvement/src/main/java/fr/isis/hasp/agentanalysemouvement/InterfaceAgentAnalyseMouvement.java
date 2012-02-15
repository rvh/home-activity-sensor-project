package fr.isis.hasp.agentanalysemouvement;

public class InterfaceAgentAnalyseMouvement {

	private Main threadAgentAnalyseMouvement = new Main();
	
	public InterfaceAgentAnalyseMouvement(){
		threadAgentAnalyseMouvement.start();
	}
	
	public void start(){
		threadAgentAnalyseMouvement.setRunning(true);
		System.out.println("Start");
	}
	
	public void stop(){
		threadAgentAnalyseMouvement.setRunning(false);
		System.out.println("Stop");
	}
}
