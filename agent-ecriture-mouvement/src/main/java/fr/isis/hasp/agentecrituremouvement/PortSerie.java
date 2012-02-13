package fr.isis.hasp.agentecrituremouvement;

import javax.comm.*;

import com.sun.comm.Win32Driver;

import fr.isis.hasp.ivycommunication.IvyCommunicationInterface;
import fr.isis.hasp.objetsmetier.Constantes;
import fr.isis.hasp.objetsmetier.Message;

import java.io.*;
import java.util.*;

public class PortSerie extends Thread implements SerialPortEventListener {

	// unique instance de la classe PortSerie
	private static PortSerie portcomm = null;

	private SerialPort serialPort;
	public BufferedReader fluxLecture;
	public OutputStream fluxEcriture; // flux d'�criture du port
	private boolean running;
	private CommPortIdentifier portId;

	private IvyCommunicationInterface bus;

	public static int NO_SERIAL_PORT_FOUND = -1;

	private PortSerie(IvyCommunicationInterface bus) {
		this.bus = bus;
	}

	/*
	 * Renvoie le seul objet instanci� de cette classe
	 */
	public static PortSerie getInstance(IvyCommunicationInterface bus) {

		if (portcomm == null) {
			portcomm = new PortSerie(bus);
		}
		return portcomm;
	}

	/*
	 * Permet l'arret du processus
	 */
	public void end() {
		if (portcomm != null && running) {
			running = false;
			portcomm = null;
		}
		return;
	}

	public PortSerie getPortSerie() {
		return portcomm;
	}

	/*
	 * Initialise le port serie avec un identifiant et ouvre le port
	 */
	public void init(String port) {
		// initialisation du driver
		Win32Driver w32Driver = new Win32Driver();
		w32Driver.initialize();
		// r�cup�ration de l'identifiant du port
		try {
			portId = CommPortIdentifier.getPortIdentifier(port);
		} catch (NoSuchPortException e) {
		}

		// ouverture du port
		try {
			serialPort = (SerialPort) portId.open("IvyCommPort", 2000);
		} catch (PortInUseException e) {
		}
		// r�cup�ration du flux
		try {
			fluxLecture = new BufferedReader(new InputStreamReader(
					serialPort.getInputStream()));
		} catch (IOException e) {
		}
		// ajout du listener
		try {
			serialPort.addEventListener(this);
		} catch (TooManyListenersException e) {
		}
		// parametrage du port
		serialPort.notifyOnDataAvailable(true);
		try {
			serialPort.setSerialPortParams(19200, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
		}
		// Log.TRACE(String.format("Port serie %s ouvert, attente de lecture",
		// idPortSerie));
		// r�cup�ration du flux de lecture et �criture du port
		try {
			fluxEcriture = serialPort.getOutputStream();
			fluxLecture = new BufferedReader(new InputStreamReader(
					serialPort.getInputStream()));
		} catch (IOException e) {
		}

		// Demarrage de l'ecoute
		portcomm.start();
	}

	/*
	 * Appel� quand on appelle la m�thode start() Permet l'�coute du port s�rie
	 * dans un processus ind�pendant
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		running = true;
		while (running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		// fermeture du flux et port
		try {
			fluxLecture.close();
			fluxEcriture.close();
		} catch (IOException e) {
		}
		serialPort.close();
	}

	// M�thode de gestion des �v�nements.
	public void serialEvent(SerialPortEvent event) {
		// gestion des �v�nements sur le port :
		// on ne fait rien sauf quand les donn�es sont disponibles
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			
			try {
				String idCapteur = (String) fluxLecture.readLine();

				String[] numero = idCapteur.split("DETX");

				int num = Integer.parseInt(numero[1]);

				Message message = new Message();
				message.setCategorieMessage(Constantes.CAPTEUR_MOUVEMENT);
				message.setDateMessage(new Date());
				message.setNumeroCapteur(num);
				message.setMessage("1");

				bus.postMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;
		}
	}
}
