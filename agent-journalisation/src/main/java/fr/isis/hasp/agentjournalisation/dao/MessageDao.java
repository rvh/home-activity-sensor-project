package fr.isis.hasp.agentjournalisation.dao;

import fr.isis.hasp.agentjournalisation.business.Message;

public interface MessageDao {

	public Message saveMessage(Message message);
	
}
