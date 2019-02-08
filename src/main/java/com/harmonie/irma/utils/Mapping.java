package com.harmonie.irma.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.harmonie.irma.dto.Stat;
import com.harmonie.irma.models.Consultation;
import com.harmonie.irma.models.Optic;
import com.harmonie.irma.statics.Config;

public class Mapping {
	public static Map<String, String> type_key = new HashMap<>();
	static {
		type_key.put("generaliste","G");
		type_key.put("gynecologue","GY");
		type_key.put("ophtalmologue","O");
		type_key.put("chirurgien-anesthesiste","CA");
		type_key.put("cardiologue","C");
		type_key.put("pediatre","P");
		type_key.put("neuropsychiatre","N");
		type_key.put("dermatologue","D");
		type_key.put("autre","A");
		type_key.put(null, null);
	}
	
	public static String get_type_key(String type) {
		return type_key.get(type);
	}
	
	public static String get_referring_key(String referring) {
		if(referring==null) {
			return null;
		}else {
			return "oui".equals(referring)?"TR":"NT";
		}
	}
	
	public static String get_course_key(String referring, String redirected_by_reffering, String declared_reffering) {
	    if("oui".equals(referring)) {
	    	return "PS";
	    }
	    if(redirected_by_reffering==null) {
	    	return null;
	    }
	    if("oui".equals(redirected_by_reffering)) {
	    	return "PS";
	    }
	    if(declared_reffering==null) {
	    	return null;
	    }
	    if("oui".equals(declared_reffering)) {
	    	return "PS";
	    }
	    return "HP";
	}
	
	public static String get_sector_key(String overrun_fees, String controlled_pricing) {
	    if(overrun_fees==null) {
	    	return null;
	    }
	    if("non".equals(overrun_fees)) {
	    	return "S1";
	    }
	    if(controlled_pricing==null) {
	    	return "S4";
	    }
	    if("non".equals(controlled_pricing)) {
	    	return "S2";
	    }
	    if("oui".equals(controlled_pricing)) {
	    	return "S3";
	    }
	    return "S4";
	}
	
	public static String get_child_key(String age, String type) {
		if(age==null) {
	    	return null;
	    }
		int age1 = Integer.parseInt(age);
	    if("pediatre".equals(type)) {
	    	if(age1<2) {
	    		return "E2";
	    	}
	    	if(age1<6) {
	    		return "E3";
	    	}
	    }else {
	    	if(age1<6) {
	    		return "E0";
	    	}
	    	if(age1<16) {
	    		return "E1";
	    	}
	    }
		return null;
	}
	
	public static String consultation_answer_to_key(String age, String type, String referring, String redirected_by_reffering,
			String declared_reffering, String overrun_fees, String controlled_pricing) {
		List<String> list = new ArrayList<>();
		list.add(get_child_key(age, type));
		list.add(get_type_key(type));
		list.add(get_referring_key(referring));
		list.add(get_course_key(referring, redirected_by_reffering, declared_reffering));
		list.add(get_sector_key(overrun_fees, controlled_pricing));
		list.removeAll(Collections.singleton(null));
		
		return String.join(" – ", list);
	}
	
	public static String get_glasses_complexity(String correction) {
		int abs_correction = Integer.parseInt(correction);
	    
	    if(abs_correction <= 6) {
	    	return "S";
	    }
	    if(abs_correction <= 8) {
	    	return "C";
	    }
	    
	    return "T";
	}
	
	public static String get_glasses_focal(String type) throws Exception {
		if("simple".equals(type)) {
			return "UF";
		}else if(type.equals("progressif")) {
			return "MF";
		}else {
			throw new Exception("type must be either simple or progressif got:"  + type);
		}
	}
	
	public static List<String[]> parse_glasses(String glasses) throws Exception {
		if(glasses==null) {
			return null;
		}else {
			List<String[]> list = new ArrayList<>();
			String[] glasse = glasses.split("_");
			Pattern pattern = Pattern.compile("(simple|progressif)((\\+|\\-)?\\d+)");
			for(String str: glasse) {
				Matcher matcher = pattern.matcher(str);
				while (matcher.find()) {
					list.add(new String[] {get_glasses_focal(matcher.group(1)), get_glasses_complexity(matcher.group(2))});
				}
			}
			return list;
		}
	}
	
	public static String optic_answer_to_key(String age, String equipment, String glasses) throws Exception {
		String age_key = Integer.parseInt(age)>=18?"A":"E";
		if("monture".equals(equipment)){
			return age_key + "_M";
		}
		List<String> glasses_key = parse_glasses(glasses).stream().map(glasse -> age_key + "_V_" + glasse[0] + "_" + glasse[1]).collect(Collectors.toList());
		if("verres".equals(equipment)) {
			return String.join("/", glasses_key.stream().sorted().collect(Collectors.toList()));
		}
		glasses_key.add(age_key + "_M");
	    return String.join("/", glasses_key.stream().sorted().collect(Collectors.toList()));
	}

	public static float parse_string_to_float(String value) {
		return Float.parseFloat(value.replaceAll(",", "."));
	}
	
	public static Float[] parse_string_list_to_float(String[]string_list){
		Float[] float_list = new Float[string_list.length];
		for(int i=0;i<string_list.length;i++) {
			float_list[i]=parse_string_to_float(string_list[i]);
		}
		return float_list;
	}
	
	public static List<Float> parse_brss(List<String> brss_string_list){
		Set<Float> result = new HashSet<>();
		Set<String> str_set = new HashSet<>(brss_string_list);
		for(String str : str_set) {
			Float[] float_list = parse_string_list_to_float(str.replaceAll(" ", "").split("/"));
			for(float item: float_list) {
				result.add(item);
			}
		}
		List<Float> list = new ArrayList<Float>();
		list.addAll(result);
		return list;
	}
	
	public static Float[] parse_rates(String[] rates) {
		Float[] float_list = new Float[rates.length];
		for(int i=0;i<rates.length;i++) {
			float_list[i]=parse_string_to_float(rates[i].replaceAll("%", ""))/100;
		}
		return float_list;
	}
	
	public static Stat mapToStat(Object object, String care_type, String equipment) {
		Stat stat = new Stat();
		if(object instanceof Consultation) {
			Consultation consultation = (Consultation) object;
			stat.setId(consultation.getId().getId().equals("S3")?"optam":"non-optam");
			stat.setPourcentage_reste_a_charge_par_quartile(getPourcentage_rest_a_charge(consultation.getPourcentageResteAChargeParQuartile()));
			stat.setRemboursement_securite_sociale((double)Math.round(consultation.getRemboursementSecuriteSociale()*100)/100);
			stat.setRemboursement_mutuelle(consultation.getRemboursementMutuelle());
			stat.setDepense_plus_frequente(consultation.getDepensePlusFrequente());
			stat.setDepense_maximale(consultation.getDepenseMaximale());
			stat.setReste_a_charge_maximum(consultation.getResteAChargeMaximum());
			
		}else if(object instanceof Optic) {
			Optic optic = (Optic) object;
			stat.setId(optic.getId().getBonification()?"kalivia":"non-kalivia");
			stat.setPourcentage_reste_a_charge_par_quartile(getPourcentage_rest_a_charge(optic.getPourcentageResteAChargeParQuartile()));
			stat.setRemboursement_securite_sociale((double)Math.round(optic.getRemboursementSecuriteSociale()*100)/100);
			stat.setRemboursement_mutuelle(optic.getRemboursementMutuelle());
			stat.setDepense_plus_frequente(optic.getDepensePlusFrequente());
			stat.setDepense_maximale(optic.getDepenseMaximale());
			stat.setReste_a_charge_maximum(optic.getResteAChargeMaximum());
		}
		stat.setQuartiles(Mapping.get_limiter_from_values(Mapping.get_quartile(care_type, equipment)));
		
		return stat;
	}
	
	public static int[] get_quartile(String care_type, String equipment) {
	    if(care_type.equals("consultation")){
	    	return Config.CONSULTATION_QUARTILE;
	    }
		if(equipment.equals("montures")){
	    	return Config.OPTIC_FRAME_QUARTILE;
	    }
		if(equipment.equals("verres")){
	    	return Config.OPTIC_GLASSES_QUARTILE;
	    }

	    return Config.OPTIC_GLASSES_FRAME_QUARTILE;
	}
	
	public static List<Map<String, Integer>> get_limiter_from_values(int[] values){
		List<Map<String, Integer>> result = new ArrayList<>();
		Map<String, Integer> first  =  new HashMap<>();
		first.put("min", null);
		first.put("max", values[0]);
		result.add(first);
		
		for(int i=0;i<values.length;i++) {
			Map<String, Integer> next  =  new HashMap<>();
			next.put("min", values[i]);
			if(i==values.length-1) {
				next.put("max", null);
			}else {
				next.put("max", values[i+1]);				
			}
			result.add(next);
		}
		return result;
	}
	
	public static Double[] getPourcentage_rest_a_charge(Double[] pourcentageResteAChargeParQuartile) {
		Double[] result = new Double[pourcentageResteAChargeParQuartile.length];
		for(int i=0;i<pourcentageResteAChargeParQuartile.length;i++) {
			if(pourcentageResteAChargeParQuartile!=null) {
				result[i]=(double) Math.round(pourcentageResteAChargeParQuartile[i]*100);
			}
		}
		return result;
	}
	
}
