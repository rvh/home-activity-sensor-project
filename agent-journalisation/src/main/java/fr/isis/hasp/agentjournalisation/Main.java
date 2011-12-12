package fr.isis.hasp.agentjournalisation;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.isis.hasp.agentjournalisation.business.Message;
import fr.isis.hasp.agentjournalisation.dao.MessageDao;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathResource resource = new ClassPathResource("spring-config.xml");
		XmlBeanFactory factory = new XmlBeanFactory(resource);

		MessageDao messageDao = (MessageDao) factory.getBean("messageDao");

		Message message = new Message();
		message.setCategorie("CapteurMouvement");

		messageDao.saveMessage(message);

		System.out.println(message.getId() + " - " + message.getCategorie());
	}

}
