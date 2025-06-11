package org.inneo.api_onibusgo.services;

import java.util.List;
import lombok.AllArgsConstructor;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.inneo.api_onibusgo.domains.Rota;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.inneo.api_onibusgo.repositories.RotaRep;

@Service 
@Transactional
@AllArgsConstructor
public class RotaServices {
	private final RotaRep rotaRep;
	
	public Rota create(Rota request) {		
	    return rotaRep.saveAndFlush(request);
	}
	
	public Rota update(Rota request) {	
		Rota rota = rotaRep.getReferenceById(request.getId());		
		if(rota == null)  throw new RuntimeException("Rota not found");
		BeanUtils.copyProperties(request, rota);
		return rotaRep.saveAndFlush(rota);
		
	}
	
	public Rota findID(Long id) {
		Rota response = rotaRep.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Not found with id"));
		return response;
	}

	public List<Rota> findAll() {
		return rotaRep.findAll();
	}	
	
	public void delete(Long id) {	
		Rota response = rotaRep.getReferenceById(id);
		if(response != null) rotaRep.deleteById(response.getId());
	}
}
