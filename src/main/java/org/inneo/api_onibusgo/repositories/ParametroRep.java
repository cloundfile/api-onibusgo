package org.inneo.api_onibusgo.repositories;

import java.util.List;
import org.inneo.api_onibusgo.domains.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParametroRep extends JpaRepository<Parametro, Long>, JpaSpecificationExecutor<Parametro>{
	List<Parametro> findByCodigo(String codigo);

}
