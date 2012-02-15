package fr.isis.hasp.agentanalyseendormissement;

public class InterfaceAgentAnalyseEndormissement {

	private Main threadAgentAnalyseEndormissement = new Main();
	
	public InterfaceAgentAnalyseEndormissement(){
		threadAgentAnalyseEndormissement.start();
	}
	
	public void start(){
		threadAgentAnalyseEndormissement.setRunning(true);
		System.out.println("Start");
	}
	
	public void stop(){
		threadAgentAnalyseEndormissement.setRunning(false);
		System.out.println("Stop");
	}
}
