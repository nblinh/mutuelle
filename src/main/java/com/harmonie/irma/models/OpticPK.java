package com.harmonie.irma.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the optic database table.
 * 
 */
@Embeddable
public class OpticPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String niveau;

	private Boolean bonification;

	public OpticPK() {
	}
	
	public OpticPK(String id, String niveau, Boolean bonification) {
		this.id = id;
		this.niveau= niveau;
		this.bonification=bonification;
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
	public Boolean getBonification() {
		return this.bonification;
	}
	public void setBonification(Boolean bonification) {
		this.bonification = bonification;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OpticPK)) {
			return false;
		}
		OpticPK castOther = (OpticPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.niveau.equals(castOther.niveau)
			&& this.bonification.equals(castOther.bonification);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.niveau.hashCode();
		hash = hash * prime + this.bonification.hashCode();
		
		return hash;
	}
}