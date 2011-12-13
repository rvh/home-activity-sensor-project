package fr.isis.hasp.agentjournalisation;

import java.util.Date;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.isis.hasp.agentjournalisation.dao.MessageDao;
import fr.isis.hasp.objetsmetier.Message;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathResource resource = new ClassPathResource("spring-config.xml");
		XmlBeanFactory factory = new XmlBeanFactory(resource);

		MessageDao messageDao = (MessageDao) factory.getBean("messageDao");

		Message message = new Message();
		message.setCategorieCapteur("CapteurMouvement");
		message.setDateMessage(new Date());
		message.setNumeroCapteur(1);

		messageDao.saveMessage(message);

		System.out.println(message.getIdMessage() + " - " + message.getCategorieCapteur());
	}

}
