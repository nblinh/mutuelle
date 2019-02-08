package com.harmonie.irma.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stat {
	private String id;
	private Double[] pourcentage_reste_a_charge_par_quartile;
	private double remboursement_securite_sociale;
	private double remboursement_mutuelle;
	private Double[] depense_plus_frequente;
	private double depense_maximale;
	private double reste_a_charge_maximum;
	private List<Map<String, Integer>> quartiles;
}
