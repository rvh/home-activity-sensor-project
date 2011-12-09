package fr.isis.hasp.agentecrituremouvement;

import javax.comm.*;

import com.sun.comm.Win32Driver;

import fr.isis.hasp.ivycommunication.IvyCommunicationInterface;

import java.io.*;
import java.util.*;

public class PortSerie extends Thread implements SerialPortEventListener {

	// unique instance de la classe PortSerie
	private static PortSerie portcomm = null;

	private SerialPort serialPort;
	public BufferedReader fluxLecture;
	public OutputStream fluxEcriture; // flux d'écriture du port
	private boolean running;
	private CommPortIdentifier portId;

	private IvyCommunicationInterface bus;

	public static int NO_SERIAL_PORT_FOUND = -1;

	private PortSerie(IvyCommunicationInterface bus) {
		this.bus = bus;
	}

	/*
	 * Renvoie le seul objet instancié de cette classe
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
		// récupération de l'identifiant du port
		try {
			portId = CommPortIdentifier.getPortIdentifier(port);
		} catch (NoSuchPortException e) {
		}

		// ouverture du port
		try {
			serialPort = (SerialPort) portId.open("IvyCommPort", 2000);
		} catch (PortInUseException e) {
		}
		// récupération du flux
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
		// récupération du flux de lecture et écriture du port
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
	 * Appelé quand on appelle la méthode start() Permet l'écoute du port série
	 * dans un processus indépendant
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

	// Méthode de gestion des événements.
	public void serialEvent(SerialPortEvent event) {
		// gestion des événements sur le port :
		// on ne fait rien sauf quand les données sont disponibles
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
			String idCapteur = new String("");
			try {
				idCapteur = (String) fluxLecture.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String[] message = { "HAS", "CapteurMouvement", idCapteur, "1" };
			
			bus.postMessage("::", message);

			break;
		}
	}
}
