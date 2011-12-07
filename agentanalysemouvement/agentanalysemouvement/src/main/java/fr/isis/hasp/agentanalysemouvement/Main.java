package fr.isis.hasp.agentanalysemouvement;

import java.util.Date;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;

import fr.isis.hasp.agentanalysemouvement.events.Mouvement;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Agent Analyse Mouvement");
		Configuration config = new Configuration();
		config.addEventTypeAutoName("fr.isis.hasp.agentanalysemouvement.events");
		EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider(config);

		String expression1 = "insert into FiltreMouvements select idCapteur as id from Mouvement.win:length(4) group by idCapteur having count(idCapteur) >= 3";

		EPStatement statement1 = epService.getEPAdministrator().createEPL(
				expression1);

		String expression2 = "select b.id as id from pattern [every a=FiltreMouvements -> b=FiltreMouvements(a.id != b.id)]";

		EPStatement statement2 = epService.getEPAdministrator().createEPL(
				expression2);

		statement1.addListener(new StatementAwareUpdateListener() {

			public void update(EventBean[] newEvents, EventBean[] oldEvents,
					EPStatement stmt, EPServiceProvider service) {
				EventBean event = newEvents[0];
				System.out.println("= " + event.get("id"));
			}
		});

		statement2.addListener(new StatementAwareUpdateListener() {

			public void update(EventBean[] newEvents, EventBean[] oldEvents,
					EPStatement stmt, EPServiceProvider service) {
				EventBean event = newEvents[0];
				System.out.println("==> " + event.get("id"));
			}
		});

		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_2", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_2", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_2", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_2", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_2", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_2", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
		epService.getEPRuntime().sendEvent(
				new Mouvement("capteur_1", new Date()));
	}

}
