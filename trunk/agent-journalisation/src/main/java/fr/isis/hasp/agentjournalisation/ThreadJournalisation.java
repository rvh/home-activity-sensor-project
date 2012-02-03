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

public class ThreadJournalisation extends Thread {

	private IvyCommunicationInterface ivy = null;
	private ClassPathResource resource = null;
	private XmlBeanFactory factory = null;
	private MessageDao messageDao = null;

	public ThreadJournalisation() {
		ivy = IvyCommunication
				.getIvyCommunicationProxy("AgentAnalyseMouvement");
		resource = new ClassPathResource("spring-config.xml");
		factory = new XmlBeanFactory(resource);
		messageDao = (MessageDao) factory.getBean("messageDao");

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

					System.out.println("MESSAGE=>DB - " + message.toString());
				}
			}
		});
	}

	public void run() {
		
		while(!interrupted()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}

	}
}
