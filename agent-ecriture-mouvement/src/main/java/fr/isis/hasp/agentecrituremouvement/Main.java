package fr.isis.hasp.agentecrituremouvement;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		InterfaceAgentEcritureMouvement tmp = new InterfaceAgentEcritureMouvement();
		tmp.start();
		
		Thread.sleep(2000);
		
		tmp.stop();
		
		Thread.sleep(10000);
		
		tmp.start();
		
		Thread.sleep(2000);
	}
	
}
