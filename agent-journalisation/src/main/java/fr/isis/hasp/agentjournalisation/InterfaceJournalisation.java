package fr.isis.hasp.agentjournalisation;

public class InterfaceJournalisation {
	
	private ThreadJournalisation journal = null;
	
	public InterfaceJournalisation(){
	}
	
	public void start(){
		journal = new ThreadJournalisation();
		journal.start();
	}
	
	public void stop(){
		journal.interrupt();
		journal = null;
	}

}
