package com.harmonie.irma.statics;

public class Question {
	public static final String controlled_pricing_question = "{"
			+ "\"name\": \"controlled_pricing\","
			+ "\"question\": \"Est-ce qu'il adhère à l'option de pratique tarifaire maîtrisée ?\","
			+ "\"gabarit\": \"bouton\","
			+ "\"answers\": ["
				+ "{ \"value\": \"oui\", \"label\": \"Oui\" },"
				+ "{ \"value\": \"non\", \"label\": \"Non\" },"
				+ "{ \"value\": \"je-ne-sais-pas\", \"label\": \"Je ne sais pas\" }"
			+ "]"
		+ "}";

	public static final String overrun_fees_question = "{"
			+ "\"name\": \"overrun_fees\","
			+ "\"question\": \"Est-ce que ce praticien pratique les dépassements d'honoraires ?\","
			+ "\"gabarit\": \"bouton\","
			+ "\"answers\": ["
				+ "{ \"value\": \"oui\", \"label\": \"Oui\" },"
				+ "{ \"value\": \"non\", \"label\": \"Non\" }"
			+ "],"
			+ "\"nextQuestion\": {"
				+ "\"oui\": "+controlled_pricing_question+","
			+ "}"
		+ "}";

	public static final String declared_reffering_question = "{"
			+ "\"name\": \"declared_reffering\","
			+ "\"question\": \"Avez-vous un médecin traitant déclaré ?\","
			+ "\"gabarit\": \"bouton\","
			+ "\"answers\": ["
				+ "{ \"value\": \"oui\", \"label\": \"Oui\" },"
				+ "{ \"value\": \"non\", \"label\": \"Non\" }"
			+ "],"
			+ "\"nextQuestion\": {"
				+ "\"oui\": "+overrun_fees_question+","
			+ "}"
		+ "}";

	public static final String redirected_by_reffering_question = "{"
			+ "\"name\": \"redirected_by_reffering\","
			+ "\"question\": \"Consultez-vous suite à l'orientation de votre médecin traitant ?\","
			+ "\"gabarit\": \"bouton\","
			+ "\"answers\": [{ \"value\": \"oui\", \"label\": \"Oui\" }, { \"value\": \"non\", \"label\": \"Non\" }],"
			+ "\"nextQuestion\": {"
				+ "\"oui\": "+overrun_fees_question+","
				+ "\"non\": "+declared_reffering_question
			+ "}"
		+ "}";

	public static final String referring_question = "{"
			+ "\"name\": \"referring\","
			+ "\"question\": \"Est-ce votre médecin traitant ?\","
			+ "\"gabarit\": \"bouton\","
			+ "\"answers\": [{ \"value\": \"oui\", \"label\": \"Oui\" }, { \"value\": \"non\", \"label\": \"Non\" }],"
			+ "\"nextQuestion\": {"
				+ "\"oui\": "+overrun_fees_question+","
				+ "\"non\": "+redirected_by_reffering_question
			+ "}"
		+ "}";

	public static final String consultation_question = "{"
			+ "\"name\": \"type\","
			+ "\"question\": \"Quel praticien souhaitez-vous consulter ?\","
			+ "\"gabarit\": \"hexagone\","
			+ "\"answers\": ["
				+ "{ \"value\": \"generaliste\", \"label\": \"Généraliste\" },"
				+ "{ \"value\": \"gynecologue\", \"label\": \"Gynécologue\" },"
				+ "{ \"value\": \"ophtalmologue\", \"label\": \"Ophtalmologue\" },"
				+ "{ \"value\": \"chirurgien-anesthesiste\", \"label\": \"Chirurgien Anésthésiste\" },"
				+ "{ \"value\": \"cardiologue\", \"label\": \"Cardiologue\" },"
				+ "{ \"value\": \"pediatre\", \"label\": \"Pédiatre\" },"
				+ "{\"value\": \"neuropsychiatre\", \"label\": \"Neuropsychiatre / Psychiatre\"},"
				+ "{ \"value\": \"dermatologue\", \"label\": \"Dermatologue\" },"
				+ "{ \"value\": \"autre\", \"label\": \"Autres spécialités\" }"
			+ "],"
			+ "\"nextQuestion\": {"
				+ "\"generaliste\": "+referring_question+","
				+ "\"gynecologue\": "+referring_question+","
				+ "\"ophtalmologue\": "+referring_question+","
				+ "\"chirurgien-anesthesiste\": "+referring_question+","
				+ "\"cardiologue\": "+referring_question+","
				+ "\"dermatologue\": "+referring_question+","
				+ "\"pediatre\": "+referring_question+","
				+ "\"neuropsychiatre\": "+referring_question+","
				+ "\"autre\": "+referring_question
			+ "}"
		+ "}";

	public static final String glasses_question = "{"
			+ "\"name\": \"glasses\","
			+ "\"question\": \"De quel type de verres avez-vous besoin ?\","
			+ "\"gabarit\": \"verre\""
		+ "}";

	public static final String optic_equipment_question = "{"
			+ "\"name\": \"equipment\","
			+ "\"question\": \"Quel équipement souhaitez-vous ?\","
			+ "\"gabarit\": \"icone\","
			+ "\"answers\": ["
				+ "{ \"value\": \"monture\", \"label\": \"Monture seule\", \"icon\": \"Optic\", \"disabled\": \"False\" },"
				+ "{ \"value\": \"monture-et-verres\", \"label\": \"Monture et verres\", \"icon\": \"FrameGlasses\", \"disabled\": \"False\" },"
				+ "{ \"value\": \"verres\", \"label\": \"Verres seuls\", \"icon\": \"Glasses\", \"disabled\": \"False\" }"
			+ "],"
			+ "\"nextQuestion\": {"
				+ "\"monture-et-verres\": "+glasses_question+","
				+ "\"verres\": "+glasses_question
			+ "}"
		+ "}";

	public static final String care_type_question = "{"
			+ "\"name\": \"care_type\","
			+ "\"question\": \"De quel type de soin s'agit-il ?\","
			+ "\"gabarit\": \"icone\","
			+ "\"answers\": ["
				+ "{ \"value\": \"consultation\", \"label\": \"Honoraires médicaux\", \"icon\": \"Analysis\", \"disabled\": \"False\" },"
				+ "{ \"value\": \"optique\", \"label\": \"Optique\", \"icon\": \"Optic\", \"disabled\": \"False\" },"
				+ "{ \"value\": \"dentaire\", \"label\": \"Dentaire\", \"icon\": \"Dentist\", \"disabled\": \"True\" },"
				+ "{ \"value\": \"medicaments\", \"label\": \"Médicaments\", \"icon\": \"Pharmacy\", \"disabled\": \"True\" },"
				+ "{ \"value\": \"hospitalisation\", \"label\": \"Hospitalisation\", \"icon\": \"Hospital\", \"disabled\": \"True\" },"
				+ "{ \"value\": \"autre\", \"label\": \"Autre\", \"icon\": \"Other\", \"disabled\": \"True\" }"
			+ "],"
			+ "\"nextQuestion\": {"
				+ "\"consultation\": "+consultation_question+","
				+ "\"optique\": "+optic_equipment_question
			+ "}"
		+ "}";

}
