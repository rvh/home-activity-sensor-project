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

public class Main extends Thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	private String nbPersonnes = "1";

	@Override
	public void run() {
		final IvyCommunicationInterface ivy = IvyCommunication
				.getIvyCommunicationProxy("AgentAnalyseMouvement");

		Configuration config = new Configuration();
		config.addEventTypeAutoName("fr.isis.hasp.objetsmetier");

		final EPServiceProvider epServiceMouvement = EPServiceProviderManager
				.getDefaultProvider(config);

		final EPServiceProvider epServiceNBPersonnes = EPServiceProviderManager
				.getDefaultProvider(config);

		/**
		 * On regarde une fenêtre de 4 évènements de type mouvement. Si parmi
		 * les 4 évènements au moins 3 concernent la même pièce, alors on stocke
		 * cet évènement dans le flux temporaire : FiltreMouvements.
		 */
		String expression1 = "insert into FiltreMouvements "
				+ "select numeroCapteur as id " + "from Message.win:length(4) "
				+ "group by numeroCapteur "
				+ "having count(numeroCapteur) >= 3";

		EPStatement statement1 = epServiceMouvement.getEPAdministrator()
				.createEPL(expression1);

		/**
		 * On regarde le chaque évènement du flux FiltreMouvements. Si les deux
		 * derniers ne concernent pas la même pièce, alors cet évènement est un
		 * changement de pièce.
		 */
		String expression2 = "select b.id as id "
				+ "from pattern "
				+ "[every a=FiltreMouvements -> b=FiltreMouvements(a.id != b.id)]";

		EPStatement statement2 = epServiceMouvement.getEPAdministrator()
				.createEPL(expression2);

		/**
		 * 
		 */
		String expression3 = "select count(message), message "
				+ "from Message.win:length(3) " + "group by message "
				+ "having count(message) >= 2";

		EPStatement statement3 = epServiceMouvement.getEPAdministrator()
				.createEPL(expression3);

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

				if(nbPersonnes.equals("1")){
					ivy.postMessage(message);
				}
			}
		});

		statement3.addListener(new StatementAwareUpdateListener() {

			public void update(EventBean[] newEvents, EventBean[] oldEvents,
					EPStatement stmt, EPServiceProvider service) {
				EventBean event = newEvents[0];
				nbPersonnes = (String) event.get("message");
//				System.out.println("nbPersonnes:" + nbPersonnes + " - "
//						+ event.get("count(message)"));
			}
		});

		ivy.subscribeMessage("^" + Constantes.NOM_PROJET
				+ Constantes.SEPARATEUR + "(.*)", new IvyMessageListener() {

			public void receive(IvyClient arg0, String[] arg1) {
				try {
					Message message = null;

					try {
						message = IvyCommunication.unSerialyzeMessage(arg1[0]);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (message != null) {
						if (message.getCategorieMessage().equals(
								Constantes.CAPTEUR_MOUVEMENT)) {
							epServiceMouvement.getEPRuntime()
									.sendEvent(message);
						} else if (message.getCategorieMessage().equals(
								Constantes.CAPTEUR_CO2)) {
							epServiceNBPersonnes.getEPRuntime().sendEvent(
									message);
						}

					}
					// String[] result = arg1[0].split(Constantes.SEPARATEUR);
					//
					// String[] numero = result[0].split("DETX");
					//
					// int num = Integer.parseInt(numero[0]);
					//
					// Message message = new Message();
					// message.setCategorieMessage(Constantes.CAPTEUR_MOUVEMENT);
					// message.setDateMessage(new Date());
					// message.setNumeroCapteur(num);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		// TEST
		//
		// Message message = new Message();
		// message.setCategorieMessage(Constantes.CAPTEUR_MOUVEMENT);
		// message.setDateMessage(new Date());
		// message.setNumeroCapteur(1);
		//
		// Message message2 = new Message();
		// message.setCategorieMessage(Constantes.CAPTEUR_MOUVEMENT);
		// message2.setDateMessage(new Date());
		// message2.setNumeroCapteur(2);
		//
		// epService.getEPRuntime().sendEvent(message);
		// epService.getEPRuntime().sendEvent(message);
		// epService.getEPRuntime().sendEvent(message);
		// epService.getEPRuntime().sendEvent(message);
		// epService.getEPRuntime().sendEvent(message2);
		// epService.getEPRuntime().sendEvent(message2);
		// epService.getEPRuntime().sendEvent(message);
		// epService.getEPRuntime().sendEvent(message2);

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

//		Message messageNB = new Message();
//		messageNB.setCategorieMessage(Constantes.CAPTEUR_CO2);
//		messageNB.setDateMessage(new Date());
//		messageNB.setNumeroCapteur(0);
//		messageNB.setMessage("1");
//		Message messageNB2 = new Message();
//		messageNB2.setCategorieMessage(Constantes.CAPTEUR_CO2);
//		messageNB2.setDateMessage(new Date());
//		messageNB2.setNumeroCapteur(0);
//		messageNB2.setMessage("2");
//
//		epServiceNBPersonnes.getEPRuntime().sendEvent(messageNB);
//		epServiceNBPersonnes.getEPRuntime().sendEvent(messageNB);
//		epServiceNBPersonnes.getEPRuntime().sendEvent(messageNB);
//		epServiceNBPersonnes.getEPRuntime().sendEvent(messageNB2);
//		epServiceNBPersonnes.getEPRuntime().sendEvent(messageNB2);
	}
}
