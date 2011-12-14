package fr.isis.hasp.agentanalysemouvement;

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

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final IvyCommunicationInterface ivy = IvyCommunication
				.getIvyCommunicationProxy("AgentAnalyseMouvement");

		Configuration config = new Configuration();
		config.addEventTypeAutoName("fr.isis.hasp.objetsmetier");
		final EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider(config);

		String expression1 = "insert into FiltreMouvements select numeroCapteur as id from Message.win:length(4) group by numeroCapteur having count(numeroCapteur) >= 3";

		EPStatement statement1 = epService.getEPAdministrator().createEPL(
				expression1);

		String expression2 = "select b.id as id from pattern [every a=FiltreMouvements -> b=FiltreMouvements(a.id != b.id)]";

		EPStatement statement2 = epService.getEPAdministrator().createEPL(
				expression2);

		statement1.addListener(new StatementAwareUpdateListener() {

			public void update(EventBean[] newEvents, EventBean[] oldEvents,
					EPStatement stmt, EPServiceProvider service) {
				EventBean event = newEvents[0];
				System.out.println("=> " + event.get("id"));
			}
		});

		statement2.addListener(new StatementAwareUpdateListener() {

			public void update(EventBean[] newEvents, EventBean[] oldEvents,
					EPStatement stmt, EPServiceProvider service) {
				EventBean event = newEvents[0];

				Message message = new Message();
				message.setCategorieMessage(Constantes.CHANGEMENT_PIECE);
				message.setDateMessage(new Date());
				message.setNumeroCapteur((Integer) event.get("id"));

				System.out.println("POST : " + message);

				ivy.postMessage(message);
			}
		});

		// TODO Serialisation et de serialisation
		ivy.subscribeMessage("^" + Constantes.NOM_PROJET
				+ Constantes.SEPARATEUR + Constantes.CAPTEUR_MOUVEMENT
				+ Constantes.SEPARATEUR + "(.*)", new IvyMessageListener() {

			public void receive(IvyClient arg0, String[] arg1) {
				try {
					String[] result = arg1[0].split(Constantes.SEPARATEUR);

					String[] numero = result[0].split("DETX");

					int num = Integer.parseInt(numero[0]);

					Message message = new Message();
					message.setCategorieMessage(Constantes.CAPTEUR_MOUVEMENT);
					message.setDateMessage(new Date());
					message.setNumeroCapteur(num);

					epService.getEPRuntime().sendEvent(message);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		// TEST
		//
		// Message message = new Message();
		// message.setCategorieCapteur("CapteurMouvement");
		// message.setDateMessage(new Date());
		// message.setNumeroCapteur(1);
		//
		// Message message2 = new Message();
		// message2.setCategorieCapteur("CapteurMouvement");
		// message2.setDateMessage(new Date());
		// message2.setNumeroCapteur(2);
		//
		// epService.getEPRuntime().sendEvent(message);
		// epService.getEPRuntime().sendEvent(message);
		// epService.getEPRuntime().sendEvent(message);
		//
		// epService.getEPRuntime().sendEvent(message2);
		// epService.getEPRuntime().sendEvent(message2);
		// epService.getEPRuntime().sendEvent(message2);
		// epService.getEPRuntime().sendEvent(message2);
		// epService.getEPRuntime().sendEvent(message2);
		// epService.getEPRuntime().sendEvent(message2);
		//
		// epService.getEPRuntime().sendEvent(message);
		// epService.getEPRuntime().sendEvent(message);
		// epService.getEPRuntime().sendEvent(message);

	}

}
