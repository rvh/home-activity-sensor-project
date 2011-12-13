package fr.isis.hasp.agentjournalisation;

import java.util.Date;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyMessageListener;
import fr.isis.hasp.agentjournalisation.dao.MessageDao;
import fr.isis.hasp.ivycommunication.IvyCommunication;
import fr.isis.hasp.ivycommunication.IvyCommunicationInterface;
import fr.isis.hasp.objetsmetier.Constantes;
import fr.isis.hasp.objetsmetier.Message;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final IvyCommunicationInterface ivy = IvyCommunication
				.getIvyCommunicationProxy("AgentAnalyseMouvement");

		ClassPathResource resource = new ClassPathResource("spring-config.xml");
		XmlBeanFactory factory = new XmlBeanFactory(resource);

		final MessageDao messageDao = (MessageDao) factory
				.getBean("messageDao");

		// Abonnement à des messages
		ivy.subscribeMessage("^" + Constantes.NOM_PROJET
				+ Constantes.SEPARATEUR + "(.*)", new IvyMessageListener() {

			public void receive(IvyClient arg0, String[] arg1) {
				String[] result = arg1[0].split(Constantes.SEPARATEUR);

				// categorieMessage
				String categorie = null;
				try {
					categorie = result[0];
				} catch (Exception e) {
				}
				//TODO Serialisation et de serialisation
				// categorieMessage
				Date date = null;
				try {
					categorie = result[0];
				} catch (Exception e) {
				}

				Message message = new Message();
				message.setCategorieMessage(Constantes.CAPTEUR_MOUVEMENT);
				message.setDateMessage(new Date());
				message.setNumeroCapteur(1);

				messageDao.saveMessage(message);

				System.out.println(message.getIdMessage() + " - "
						+ message.getCategorieMessage());
			}

		});

	}

}
