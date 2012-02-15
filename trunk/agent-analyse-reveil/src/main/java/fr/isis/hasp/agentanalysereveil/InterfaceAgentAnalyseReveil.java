package fr.isis.hasp.agentanalysereveil;

public class InterfaceAgentAnalyseReveil {

	private Main threadAgentAnalyseReveil = new Main();
	
	public InterfaceAgentAnalyseReveil(){
		threadAgentAnalyseReveil.start();
	}
	
	public void start(){
		threadAgentAnalyseReveil.setRunning(true);
		System.out.println("Start");
	}
	
	public void stop(){
		threadAgentAnalyseReveil.setRunning(true);
		System.out.println("Stop");
	}
}
