package fr.isis.hasp.agentjournalisation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.isis.hasp.objetsmetier.Message;

public class MessageDaoImpl implements MessageDao {

	private SessionFactory sessionFactory;
	
	private Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);
	
	public Message saveMessage(Message message) {
		logger.info("[DEBUT] saveMessage()");
		logger.info(message.toString());
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.saveOrUpdate(message);

		tx.commit();
		session.close();
		
		logger.info("[FIN] saveMessage()");
		return message;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
