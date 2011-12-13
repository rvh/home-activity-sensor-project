package fr.isis.hasp.ivycommunication;

import fr.dgac.ivy.IvyMessageListener;
import fr.isis.hasp.objetsmetier.Message;

public interface IvyCommunicationInterface {
	
	/**
	 * Publie un message sur le bus Ivy.
	 * @param separateur Séparateur.
	 * @param message Le message.
	 */
	public void postMessage(Message message);
	
	/**
	 * S'aboner à un type d'évènements.
	 * @param regexp RegExp qui fixe le type de l'évènement.
	 * @param listener Listener qui reçoit les évènemens.
	 */
	public void subscribeMessage(String regexp, IvyMessageListener listener);
	
}
