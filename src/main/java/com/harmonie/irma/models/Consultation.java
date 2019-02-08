package com.harmonie.irma.models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the consultation database table.
 * 
 */
@Entity
@Getter
@Setter
@NamedQuery(name="Consultation.findAll", query="SELECT c FROM Consultation c")
public class Consultation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConsultationPK id;

	@Column(name="depense_maximale")
	private double depenseMaximale;

	@Type(type = "JsonDoubleArrayType")
	@Column(name="depense_plus_frequente")
	private Double[] depensePlusFrequente;

	@Type(type = "JsonDoubleArrayType")
	@Column(name="pourcentage_reste_a_charge_par_quartile")
	private Double[] pourcentageResteAChargeParQuartile;

	@Column(name="remboursement_mutuelle")
	private double remboursementMutuelle;

	@Column(name="remboursement_securite_sociale")
	private double remboursementSecuriteSociale;

	@Column(name="reste_a_charge_maximum")
	private double resteAChargeMaximum;
	
	public Consultation() {
	}
}