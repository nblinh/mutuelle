package com.harmonie.irma.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harmonie.irma.models.Consultation;
import com.harmonie.irma.models.ConsultationPK;

@RepositoryRestResource(collectionResourceRel = "consultations", path = "consultations")
public interface ConsultationRepository extends CrudRepository<Consultation, ConsultationPK> {
}
