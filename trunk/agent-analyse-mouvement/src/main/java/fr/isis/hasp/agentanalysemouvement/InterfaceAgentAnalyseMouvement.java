package fr.isis.hasp.agentanalysemouvement;

public class InterfaceAgentAnalyseMouvement {

	private Main threadAgentAnalyseMouvement = null;
	
	public InterfaceAgentAnalyseMouvement(){
	}
	
	public void start(){
		threadAgentAnalyseMouvement = new Main();
		threadAgentAnalyseMouvement.start();
		System.out.println("Start");
	}
	
	public void stop(){
		threadAgentAnalyseMouvement.interrupt();
		threadAgentAnalyseMouvement = null;
		System.out.println("Stop");
	}
}
