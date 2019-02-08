package com.harmonie.irma.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the consultation database table.
 * 
 */
@Embeddable
public class ConsultationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String niveau;

	public ConsultationPK() {
	}
	
	public ConsultationPK(String id, String niveau) {
		this.id = id;
		this.niveau=niveau;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNiveau() {
		return this.niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConsultationPK)) {
			return false;
		}
		ConsultationPK castOther = (ConsultationPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.niveau.equals(castOther.niveau);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.niveau.hashCode();
		
		return hash;
	}
}