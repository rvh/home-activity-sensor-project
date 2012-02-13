package fr.isis.hasp.agentanalysereveil;

import java.util.Date;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;

import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyMessageListener;
import fr.isis.hasp.ivycommunication.IvyCommunication;
import fr.isis.hasp.ivycommunication.IvyCommunicationInterface;
import fr.isis.hasp.objetsmetier.Constantes;
import fr.isis.hasp.objetsmetier.Message;

public class Main extends Thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	@Override
	public void run() {
		System.out.println("-> Start");
		while(!interrupted()){
		final IvyCommunicationInterface ivy = IvyCommunication
				.getIvyCommunicationProxy("AgentAnalyseReveil");

		Configuration config = new Configuration();
		config.addEventTypeAutoName("fr.isis.hasp.objetsmetier");
		final EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider(config);

		/*
		 * Détecte un réveil si après un endormissement on détecte un évènement
		 * de levée du coussin.
		 */
		String expression = "select a.numeroCapteur as id from pattern "
				+ "[every a=Message(a.categorieMessage='"
				+ Constantes.ENDORMISSEMENT + "') "
				+ "-> e=Message(e.categorieMessage='"
				+ Constantes.CAPTEUR_COUSSIN + "')]";

		EPStatement statement1 = epService.getEPAdministrator().createEPL(
				expression);

		statement1.addListener(new StatementAwareUpdateListener() {

			public void update(EventBean[] newEvents, EventBean[] oldEvents,
					EPStatement stmt, EPServiceProvider service) {

				// Endormissement
				Message message = new Message();
				message.setCategorieMessage(Constantes.REVEIL);
				message.setDateMessage(new Date());
				message.setNumeroCapteur(0);

				ivy.postMessage(message);
			}
		});

		ivy.subscribeMessage("^" + Constantes.NOM_PROJET
				+ Constantes.SEPARATEUR + "(.*)", new IvyMessageListener() {

			public void receive(IvyClient arg0, String[] arg1) {
				try {
					Message message = IvyCommunication
							.unSerialyzeMessage(arg1[0]);

					epService.getEPRuntime().sendEvent(message);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		// TEST

		// Message messageEndormissement = new Message();
		// messageEndormissement.setCategorieMessage(Constantes.ENDORMISSEMENT);
		// messageEndormissement.setDateMessage(new Date());
		// messageEndormissement.setNumeroCapteur(0);
		//
		// Message messageCoussin = new Message();
		// messageCoussin.setCategorieMessage(Constantes.CAPTEUR_COUSSIN);
		// messageCoussin.setDateMessage(new Date());
		// messageCoussin.setNumeroCapteur(0);
		//
		// epService.getEPRuntime().sendEvent(messageEndormissement);
		//
		// epService.getEPRuntime().sendEvent(messageCoussin);
		}
	}

}
