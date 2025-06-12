package org.inneo.api_onibusgo.services;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;
import org.inneo.api_onibusgo.domains.Parameter;
import org.inneo.api_onibusgo.specs.ParameterSpec;
import org.inneo.api_onibusgo.repositories.ParameterRep;

@Service 
@Transactional
@AllArgsConstructor
public class ParameterService {
	private final ParameterRep parameterRep;	
	
	public Parameter create(Parameter request) {		
	    return parameterRep.saveAndFlush(request);
	}
	
	public Parameter update(Parameter request) {	
		Parameter parameter = parameterRep.findById(request.getId())
		.orElseThrow(() -> new EntityNotFoundException("ID Not found!"));
		BeanUtils.copyProperties(request, parameter);
		return parameterRep.saveAndFlush(parameter);
		
	}

	public List<Parameter> findAll() {
		return parameterRep.findAll();
	}
	
	public Parameter findID(Long id) {
		Parameter response = parameterRep.findById(id)
		.orElseThrow(() -> new EntityNotFoundException("ID not found!"));
		return response;
	}
	
	public List<Parameter> findByPage(String page, String field) {
		List<Parameter> response = parameterRep.findAll(ParameterSpec.doFilter(page, field));
		return response;
	}
	
	public void delete(Long id) {	
		Parameter parametro = parameterRep.findById(id)
		.orElseThrow(() -> new EntityNotFoundException("ID Not found!"));
		parameterRep.deleteById(parametro.getId());
	}
}
