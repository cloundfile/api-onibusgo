package org.inneo.api_onibusgo.repositories;

import org.inneo.api_onibusgo.domains.Parameter;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ParameterRep extends JpaRepository<Parameter, Long>, 
JpaSpecificationExecutor<Parameter>{}
