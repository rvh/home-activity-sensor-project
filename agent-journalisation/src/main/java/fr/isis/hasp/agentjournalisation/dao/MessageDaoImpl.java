package fr.isis.hasp.agentjournalisation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.isis.hasp.agentjournalisation.business.Message;

public class MessageDaoImpl implements MessageDao {

	private SessionFactory sessionFactory;
	Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Message saveMessage(Message message) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.saveOrUpdate(message);

		tx.commit();
		session.close();
		return message;
	}

}
