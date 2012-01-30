package fr.isis.hasp.agentjournalisation;

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
				Message message = null;

				try {
					message = IvyCommunication.unSerialyzeMessage(arg1[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (message != null) {
					// Journaliser le message
					messageDao.saveMessage(message);

					System.out.println("MESSAGE=>DB - "+message.toString());
				}
			}
		});
		
	}
	
}
