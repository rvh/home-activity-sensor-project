package fr.isis.hasp.agentanalysemouvement.events;

import java.util.Date;

public class Mouvement {

	private String idCapteur;
	private Date date;

	public Mouvement() {
	}

	public Mouvement(String idCapteur, Date date) {
		this.idCapteur = idCapteur;
		this.date = date;
	}

	/**
	 * @return the idCapteur
	 */
	public String getIdCapteur() {
		return idCapteur;
	}

	/**
	 * @param idCapteur
	 *            the idCapteur to set
	 */
	public void setIdCapteur(String idCapteur) {
		this.idCapteur = idCapteur;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
