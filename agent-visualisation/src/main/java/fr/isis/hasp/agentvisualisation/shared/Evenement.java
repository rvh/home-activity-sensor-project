package fr.isis.hasp.agentvisualisation.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Evenement implements IsSerializable{

	private Date date;
	private String dateString;
	private String categorie;
	private int numero;
	private float valeur;

	public Evenement() {
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public float getValeur() {
		return valeur;
	}

	public void setValeur(float valeur) {
		this.valeur = valeur;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

}
