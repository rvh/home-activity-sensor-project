package fr.isis.hasp.ivycommunication;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;
import fr.isis.hasp.objetsmetier.Message;

public class IvyCommunication implements IvyCommunicationInterface {

	private Ivy bus;

	private IvyCommunication(String nomDuBus) {
		if (nomDuBus != null && !"".equals(nomDuBus)) {
			bus = new Ivy(nomDuBus, nomDuBus + "Ready", null);
		} else {
			bus = new Ivy("IvyBus", nomDuBus + "IvyBusReady", null);
		}

		String address = "172.18.3.255:2010";

		try {
			bus.start(address);
		} catch (IvyException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Retourne un objet qui permet de communiquer avec le bus Ivy.
	 * 
	 * @param nomDuBus
	 *            Nom de l'instance.
	 * @return IvyCommunicationInterface.
	 */
	public static IvyCommunicationInterface getIvyCommunicationProxy(
			String nomDuBus) {
		return (IvyCommunicationInterface) new IvyCommunication(nomDuBus);
	}

	public void postMessage(String separateur, String[] message) {
		try {

			String messageString = "";
			for (String contenu : message) {
				if (contenu != null && !"".equals(contenu)) {
					if (!"".equals(messageString)) {
						messageString += separateur;
					}
					messageString += contenu;
				}
			}

			if (!"".equals(messageString)) {
				bus.sendMsg(messageString);
			}

		} catch (IvyException e) {
			e.printStackTrace();
		}
	}

	public void subscribeMessage(String regexp, IvyMessageListener listener) {
		try {
			bus.bindMsg(regexp, listener);
		} catch (IvyException e) {
			e.printStackTrace();
		}
	}

	protected void finalize() {
		bus.stop();
	}

	public void postMessage(String separateur, Message message) {
		try {

			// HAS::CategorieCapteur::NumeroCapteur::Date::QuantiteMesure
			String messageString = "HAS" + separateur
					+ message.getCategorieCapteur() + separateur
					+ message.getDateMessage() + separateur
					+ message.getNumeroCapteur() + separateur
					+ message.getQuantiteMesure();

			// for (String contenu : message) {
			// if (contenu != null && !"".equals(contenu)) {
			// if (!"".equals(messageString)) {
			// messageString += separateur;
			// }
			// messageString += contenu;
			// }
			// }

			if (!"".equals(messageString)) {
				bus.sendMsg(messageString);
				System.out.println("POST : " + messageString);
			}

		} catch (IvyException e) {
			e.printStackTrace();
		}
	}

}