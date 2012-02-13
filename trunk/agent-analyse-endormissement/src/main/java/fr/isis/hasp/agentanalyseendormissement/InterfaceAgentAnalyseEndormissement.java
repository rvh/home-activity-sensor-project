package fr.isis.hasp.agentanalyseendormissement;

public class InterfaceAgentAnalyseEndormissement {

	private Main threadAgentAnalyseEndormissement = null;
	
	public InterfaceAgentAnalyseEndormissement(){
	}
	
	public void start(){
		threadAgentAnalyseEndormissement = new Main();
		threadAgentAnalyseEndormissement.start();
		System.out.println("Start");
	}
	
	public void stop(){
		threadAgentAnalyseEndormissement.interrupt();
		threadAgentAnalyseEndormissement = null;
		System.out.println("Stop");
	}
}
