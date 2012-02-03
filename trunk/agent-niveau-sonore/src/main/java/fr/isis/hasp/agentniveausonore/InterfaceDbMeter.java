package fr.isis.hasp.agentniveausonore;

public class InterfaceDbMeter {
	
	private DbMeter threadDbM = null;
	
	public InterfaceDbMeter(){
		
	}
	
	public void start(){
		threadDbM = new DbMeter();
		threadDbM.start();
	}
	
	public void stop(){
		threadDbM.interrupt();
		threadDbM = null;
	}

}
