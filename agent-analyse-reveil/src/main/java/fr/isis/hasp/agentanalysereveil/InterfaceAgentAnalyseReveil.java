package fr.isis.hasp.agentanalysereveil;

public class InterfaceAgentAnalyseReveil {

	private Main threadAgentAnalyseReveil = null;
	
	public InterfaceAgentAnalyseReveil(){
	}
	
	public void start(){
		threadAgentAnalyseReveil = new Main();
		threadAgentAnalyseReveil.start();
		System.out.println("Start");
	}
	
	public void stop(){
		threadAgentAnalyseReveil.interrupt();
		threadAgentAnalyseReveil = null;
		System.out.println("Stop");
	}
}
