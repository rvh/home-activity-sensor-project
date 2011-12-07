package fr.isis.hasp.ivycommunication;

import fr.dgac.ivy.IvyMessageListener;

public interface IvyCommunicationInterface {
	
	/**
	 * Publie un message sur le bus Ivy.
	 * @param separateur Séparateur.
	 * @param message Les Différentes parties du message qui seront séparées par le "separateur".
	 */
	public void postMessage(String separateur,String[] message);
	
	/**
	 * S'aboner à un type d'évènements.
	 * @param regexp RegExp qui fixe le type de l'évènement.
	 * @param listener Listener qui reçoit les évènemens.
	 */
	public void subscribeMessage(String regexp, IvyMessageListener listener);
	
}
