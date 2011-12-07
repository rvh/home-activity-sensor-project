package fr.isis.hasp.ivycommunication;

import fr.dgac.ivy.IvyMessageListener;

public interface IvyCommunicationInterface {
	
	/**
	 * Publie un message sur le bus Ivy.
	 * @param separateur S�parateur.
	 * @param message Les Diff�rentes parties du message qui seront s�par�es par le "separateur".
	 */
	public void postMessage(String separateur,String[] message);
	
	/**
	 * S'aboner � un type d'�v�nements.
	 * @param regexp RegExp qui fixe le type de l'�v�nement.
	 * @param listener Listener qui re�oit les �v�nemens.
	 */
	public void subscribeMessage(String regexp, IvyMessageListener listener);
	
}
