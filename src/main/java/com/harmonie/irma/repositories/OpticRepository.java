package com.harmonie.irma.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harmonie.irma.models.Optic;
import com.harmonie.irma.models.OpticPK;

@RepositoryRestResource(collectionResourceRel = "optics", path = "optics")
public interface OpticRepository extends CrudRepository<Optic, OpticPK> {
}
