package fr.isis.hasp.agentanalyseendormissement;

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
	private static final int INTERVALE = 20;
	private static final int SEUIL_LUMIERE = 50;
	private static final int NUM_SUNSPOT_CHAMBRE = 1;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final IvyCommunicationInterface ivy = IvyCommunication
				.getIvyCommunicationProxy("AgentAnalyseEndormissement");

		Configuration config = new Configuration();
		config.addEventTypeAutoName("fr.isis.hasp.objetsmetier");
		final EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider(config);

		/*
		 * Détecte un endormissement si
		 * il y a un changement de pièce vers la pièce 3, puis sur un intervale de INTERVALE secondes,
		 * on ne détecte aucun des évènements suivant :
		 * - changement de pièce
		 * - son élevé
		 * - lumière au dessus d'un seuil : SEUIL_LUMIERE
		 * - levée du coussin
		 */
		String expression = "select a.numeroCapteur as id from pattern " +
				"[every a=Message(a.numeroCapteur=3, a.categorieMessage='"+Constantes.CHANGEMENT_PIECE+"') -> (timer:interval("+INTERVALE+" sec) " +
						"and not b=Message(b.categorieMessage='"+Constantes.CHANGEMENT_PIECE+"') " +
						"and not c=Message(c.categorieMessage='"+Constantes.CAPTEUR_SON+"', message='1') " +
						"and not d=Message(d.categorieMessage='"+Constantes.CAPTEUR_LUMIERE+"', cast(message, int)>"+SEUIL_LUMIERE+", d.numeroCapteur="+NUM_SUNSPOT_CHAMBRE+") " +
						"and not e=Message(e.categorieMessage='"+Constantes.CAPTEUR_COUSSIN+"') " +
								")]";
		
		EPStatement statement1 = epService.getEPAdministrator().createEPL(
				expression);


		statement1.addListener(new StatementAwareUpdateListener() {

			public void update(EventBean[] newEvents, EventBean[] oldEvents,
					EPStatement stmt, EPServiceProvider service) {

				// Endormissement
				Message message = new Message();
				message.setCategorieMessage(Constantes.ENDORMISSEMENT);
				message.setDateMessage(new Date());
				message.setNumeroCapteur(0);

				ivy.postMessage(message);
			}
		});

		ivy.subscribeMessage("^" + Constantes.NOM_PROJET
				+ Constantes.SEPARATEUR + "(.*)", new IvyMessageListener() {

			public void receive(IvyClient arg0, String[] arg1) {
				try {
					Message message = IvyCommunication.unSerialyzeMessage(arg1[0]);
					
					epService.getEPRuntime().sendEvent(message);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		// TEST

//		Message message1 = new Message();
//		message1.setCategorieMessage(Constantes.CHANGEMENT_PIECE);
//		message1.setDateMessage(new Date());
//		message1.setNumeroCapteur(3);
//
//		Message message2 = new Message();
//		message2.setCategorieMessage(Constantes.CHANGEMENT_PIECE);
//		message2.setDateMessage(new Date());
//		message2.setNumeroCapteur(1);
//		
//		Message messageLum = new Message();
//		messageLum.setCategorieMessage(Constantes.CAPTEUR_LUMIERE);
//		messageLum.setDateMessage(new Date());
//		messageLum.setNumeroCapteur(0);
//		messageLum.setMessage("40");
//		
//		Message messageSon = new Message();
//		messageSon.setCategorieMessage(Constantes.CAPTEUR_SON);
//		messageSon.setDateMessage(new Date());
//		messageSon.setNumeroCapteur(0);
//		messageSon.setMessage("0");
//		
//		Message messageCoussin = new Message();
//		messageCoussin.setCategorieMessage(Constantes.CAPTEUR_COUSSIN);
//		messageCoussin.setDateMessage(new Date());
//		messageCoussin.setNumeroCapteur(0);
//		messageCoussin.setMessage("0");
//		
//		epService.getEPRuntime().sendEvent(message1);
//		epService.getEPRuntime().sendEvent(message2);
//
//		epService.getEPRuntime().sendEvent(messageSon);
//		epService.getEPRuntime().sendEvent(messageLum);
//		epService.getEPRuntime().sendEvent(messageCoussin);
	}
}