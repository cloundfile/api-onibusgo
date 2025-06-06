package org.inneo.api_onibusgo.services;

import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;
import org.inneo.api_onibusgo.specs.PametroSpec;
import org.inneo.api_onibusgo.domains.Parametro;

import org.inneo.api_onibusgo.repositories.ParametroRep;

@Service 
@Transactional
public class ParametroService {
	private final ParametroRep parametroRep;
	
	public ParametroService(ParametroRep parametroRep) {
        this.parametroRep = parametroRep;
    }
	
	public Parametro create(Parametro request) {		
	    return parametroRep.saveAndFlush(request);
	}
	
	public Parametro update(Parametro request) {	
		Parametro parametro = parametroRep.getReferenceById(request.getId());		
		if(parametro == null) parametro = new Parametro();
		BeanUtils.copyProperties(request, parametro);
	    return parametroRep.saveAndFlush(parametro);
	}
	
	public List<Parametro> findAll() {
		return parametroRep.findAll();
	}
	
	public List<Parametro> findBy(String codigo) {
		List<Parametro> response = parametroRep.findAll(PametroSpec.doFilter(codigo));
		return response;
	}
	
	public void delete(Long id) {	
		Parametro parametro = parametroRep.getReferenceById(id);
		if(parametro != null) parametroRep.deleteById(parametro.getId());
	}
}
