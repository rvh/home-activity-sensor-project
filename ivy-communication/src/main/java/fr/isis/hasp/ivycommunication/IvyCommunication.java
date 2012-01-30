package fr.isis.hasp.ivycommunication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;
import fr.isis.hasp.objetsmetier.Constantes;
import fr.isis.hasp.objetsmetier.Message;

/**
 * IvyCommunication fournit des methodes d'accès au bus Ivy.
 * 
 * Format des messages : HASP::CategorieMessage::NumeroCapteur::Date::Message
 * 
 * @author rvanhoof
 */
public class IvyCommunication implements IvyCommunicationInterface {

	private Ivy bus;

	private IvyCommunication(String nomDuBus) {
		if (nomDuBus != null && !"".equals(nomDuBus)) {
			bus = new Ivy(nomDuBus, nomDuBus + "Ready", null);
		} else {
			bus = new Ivy("IvyBus", nomDuBus + "IvyBusReady", null);
		}

		try {
			bus.start(Constantes.ADRESSE);
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

	public void postMessage(Message message) {
		try {

			// HAS::CategorieCapteur::NumeroCapteur::Date::QuantiteMesure
//			DateFormat dateFormat = new SimpleDateFormat(
//					Constantes.FORMAT_DATE);
//			
//			String messageString = Constantes.NOM_PROJET
//					+ Constantes.SEPARATEUR + message.getCategorieMessage()
//					+ Constantes.SEPARATEUR + dateFormat.format(message.getDateMessage())
//					+ Constantes.SEPARATEUR + message.getNumeroCapteur()
//					+ Constantes.SEPARATEUR + message.getMessage();
			
			String messageString = serialyzeMessage(message);

			if (!"".equals(messageString)) {
				bus.sendMsg(messageString);
				System.out.println("POST : " + messageString);
			}

		} catch (IvyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Message to String
	 * @param message
	 * @return resultMessage Format : HASP::CategorieMessage::NumeroCapteur::Date::Message
	 */
	public static String serialyzeMessage(Message message) {
//		String messageString = "";
		String messageString = Constantes.NOM_PROJET + Constantes.SEPARATEUR;

		// CategorieMessage
		if (message.getCategorieMessage() != null
				&& !message.getCategorieMessage().equals("")) {
			messageString += message.getCategorieMessage() + Constantes.SEPARATEUR;

			// NumeroCapteur
			if (message.getNumeroCapteur() != null) {
				messageString += message.getNumeroCapteur()
						+ Constantes.SEPARATEUR;

				// Date
				if (message.getDateMessage() != null) {
					DateFormat dateFormat = new SimpleDateFormat(
							Constantes.FORMAT_DATE);

					messageString += dateFormat
							.format(message.getDateMessage())
							+ Constantes.SEPARATEUR;

					// Message (Optionel)
					if (message.getMessage() != null
							&& !"".equals(message.getMessage())) {
						messageString += message.getMessage();
					}

					return messageString;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * String to Message
	 * @param message Format : CategorieMessage::NumeroCapteur::Date::Message
	 * @return resultMessage
	 */
	public static Message unSerialyzeMessage(String message) {
		String[] splitMessage = message.split(Constantes.SEPARATEUR);
		if (splitMessage != null) {
			Message result = new Message();

			try {
				// CategorieMessage
				if (splitMessage[0] != null && !splitMessage[0].equals("")) {
					result.setCategorieMessage(splitMessage[0]);

					// NumeroCapteur
					if (splitMessage[1] != null && !splitMessage[1].equals("")) {
						result.setNumeroCapteur(Integer
								.parseInt(splitMessage[1]));

						// Date
						if (splitMessage[2] != null
								&& !splitMessage[2].equals("")) {
							DateFormat dateFormat = new SimpleDateFormat(
									Constantes.FORMAT_DATE);
							Date date = null;
							date = dateFormat.parse(splitMessage[2]);
							
							if(date != null){
								result.setDateMessage(date);
								
								// Message (Optionel)
								try {
									if (splitMessage[3] != null
											&& !splitMessage[3].equals("")) {
										result.setMessage(splitMessage[3]);
									}
								} catch (Exception e) {
								}
								
								// On retourne le résultat
								return result;
							}
						}
					}
				}
			} catch (Exception e) {
			}

			return null;
		} else {
			return null;
		}
	}
}