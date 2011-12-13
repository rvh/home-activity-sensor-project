package fr.isis.hasp.objetsmetier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message implements Serializable {

	private static final long serialVersionUID = 4390580697778329645L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idMessage;

	@Column(nullable=false)
	private String categorieMessage;

	@Column(nullable=false)
	private Date dateMessage;

	@Column(nullable=false)
	private Integer numeroCapteur;

	@Column(nullable=true)
	private String message;

	public Message() {
	}

	public long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(long idMessage) {
		this.idMessage = idMessage;
	}

	public String getCategorieMessage() {
		return categorieMessage;
	}

	public void setCategorieMessage(String categorieMessage) {
		this.categorieMessage = categorieMessage;
	}

	public Date getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(Date dateMessage) {
		this.dateMessage = dateMessage;
	}

	public Integer getNumeroCapteur() {
		return numeroCapteur;
	}

	public void setNumeroCapteur(Integer numeroCapteur) {
		this.numeroCapteur = numeroCapteur;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", categorieMessage="
				+ categorieMessage + ", dateMessage=" + dateMessage
				+ ", numeroCapteur=" + numeroCapteur + ", message=" + message
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idMessage ^ (idMessage >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (idMessage != other.idMessage)
			return false;
		return true;
	}

}