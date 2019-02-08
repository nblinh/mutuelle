package com.harmonie.irma.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmonie.irma.dto.Stat;
import com.harmonie.irma.models.Consultation;
import com.harmonie.irma.models.ConsultationPK;
import com.harmonie.irma.models.Optic;
import com.harmonie.irma.models.OpticPK;
import com.harmonie.irma.models.Produit;
import com.harmonie.irma.repositories.ConsultationRepository;
import com.harmonie.irma.repositories.OpticRepository;
import com.harmonie.irma.repositories.ProductRepository;
import com.harmonie.irma.utils.Mapping;

@RestController
@RequestMapping("/refunds")
public class RefundController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Autowired
	private OpticRepository opticRepository;
	
//	@GetMapping("/consultations")
//	public Iterable<Consultation> findAll() {
//		return consultationRepository.findAll();
//	}

	@GetMapping
	public List<Stat> getRefund(@RequestParam(value = "age", required = false) String age, @RequestParam(value = "product", required = true) String product,
			@RequestParam(value = "type", required = false) String type, @RequestParam(value = "referring", required = false) String referring,
			@RequestParam(value = "redirected_by_reffering", required = false) String redirected_by_reffering, 
			@RequestParam(value = "declared_reffering", required = false) String declared_reffering,
			@RequestParam(value = "overrun_fees", required = false) String overrun_fees, @RequestParam(value = "controlled_pricing", required = false) String controlled_pricing,
			@RequestParam(value = "care_type", required = false, defaultValue="consultation") String care_type, @RequestParam(value = "equipment", required = false) String equipment,
		    @RequestParam(value = "glasses", required = false) String glasses) throws Exception {
		List<Object> stats_list = new ArrayList<Object>();
		Optional<Produit> fetched_product = productRepository.findById(product);
		if(!fetched_product.isPresent()) {
			throw new EntityNotFoundException();
		}
		if(care_type.equals("consultation")) {
			if(!"je-ne-sais-pas".equals(controlled_pricing)) {
				String mapping_key = Mapping.consultation_answer_to_key(age, type, referring, redirected_by_reffering, declared_reffering, overrun_fees, controlled_pricing);
				Optional<Consultation> consultation = consultationRepository.findById(new ConsultationPK(mapping_key,fetched_product.get().getNiveauConsultation()));
				if(consultation.isPresent()) {
					stats_list.add(consultation.get());
				}
			}else {
				String mapping_key_optam = Mapping.consultation_answer_to_key(age, care_type, referring, redirected_by_reffering, declared_reffering, overrun_fees, "oui");
				String mapping_key_not_optam = Mapping.consultation_answer_to_key(age, care_type, referring, redirected_by_reffering, declared_reffering, overrun_fees, "non");
				Optional<Consultation> consultation1 = consultationRepository.findById(new ConsultationPK(mapping_key_optam,fetched_product.get().getNiveauOptique()));
				Optional<Consultation> consultation2 = consultationRepository.findById(new ConsultationPK(mapping_key_not_optam,fetched_product.get().getNiveauOptique()));
				if(consultation1.isPresent()) {
					stats_list.add(consultation1.get());
				}
				if(consultation2.isPresent()) {
					stats_list.add(consultation2.get());
				}
			}
		}else if(care_type.equals("optique")) {
			String mapping_key = Mapping.optic_answer_to_key(age, equipment, glasses);
			Optional<Optic> optic1 = opticRepository.findById(new OpticPK(mapping_key, fetched_product.get().getNiveauOptique(), true));
			Optional<Optic> optic2 = opticRepository.findById(new OpticPK(mapping_key, fetched_product.get().getNiveauOptique(), false));
			if(optic1.isPresent()) {
				stats_list.add(optic1.get());
			}
			if(optic2.isPresent()) {
				stats_list.add(optic2.get());
			}
		}
		if(stats_list.isEmpty()) {
			throw new Exception("Aucune statistique ne peut être déterminée pour les filtres donnés.");
		}else {
			List<Stat> stats = new ArrayList<>();
			for(Object object:stats_list) {
				stats.add(Mapping.mapToStat(object, care_type, equipment));
			}
			return stats;
		}
	}
}
