package fr.isis.hasp.agentnombrepersonne;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String args[]) throws InterruptedException {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		
		// </editor-fold>

		/* Create and display the form */
		final InterfaceSimulateur interfaceS = new InterfaceSimulateur();
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				interfaceS.setVisible(true);
			}
		});
		interfaceS.start();
		Thread.sleep(5000);
		interfaceS.stop();
		Thread.sleep(5000);
		interfaceS.start();
	}
}
