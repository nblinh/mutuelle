package com.harmonie.irma.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the produit database table.
 * 
 */
@Entity
@NamedQuery(name="Produit.findAll", query="SELECT p FROM Produit p")
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="niveau_consultation")
	private String niveauConsultation;

	@Column(name="niveau_optique")
	private String niveauOptique;

	public Produit() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNiveauConsultation() {
		return this.niveauConsultation;
	}

	public void setNiveauConsultation(String niveauConsultation) {
		this.niveauConsultation = niveauConsultation;
	}

	public String getNiveauOptique() {
		return this.niveauOptique;
	}

	public void setNiveauOptique(String niveauOptique) {
		this.niveauOptique = niveauOptique;
	}

}