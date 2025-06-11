package org.inneo.api_onibusgo.services;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.inneo.api_onibusgo.domains.Rota;
import org.inneo.api_onibusgo.domains.Schedule;

import org.inneo.api_onibusgo.ferramentas.utils;
import org.inneo.api_onibusgo.specs.ScheduleSpec;
import org.inneo.api_onibusgo.repositories.RotaRep;
import org.inneo.api_onibusgo.repositories.ScheduleRep;

@Service 
@Transactional
@AllArgsConstructor
public class ScheduleService {
	private final RotaRep rotaRep;
	private final ScheduleRep scheduleRep;
	
	public Schedule create(Schedule request) {		
		Optional<Rota> rota = rotaRep.findById(request.getRota().getId());	    
	    if (rota.isEmpty())throw new EntityNotFoundException("Rota not found with ID: " + request.getRota().getId());
	    request.setRota(rota.get());
	    return scheduleRep.saveAndFlush(request);
	}
	
	public Schedule update(Schedule request) {	
		Schedule schedule = scheduleRep.getReferenceById(request.getId());		
		if(schedule == null) schedule = new Schedule();
		BeanUtils.copyProperties(request, schedule);
	    return scheduleRep.saveAndFlush(schedule);
	}
	
	public List<Schedule> findAll() {
		if(utils.isWeekend()) throw new RuntimeException("Serviço indisponível nesta data.");
		return scheduleRep.findAll();
	}
	
	public Schedule findID(Long id) {
		Schedule response= scheduleRep.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Not found with id"));
		return response;
	}
	
	public List<Schedule> findRota(Long id) {
		List<Schedule> response = scheduleRep.findAll(ScheduleSpec.daRota(id));
		return response;
	}	
	
	public void delete(Long id) {	
		Schedule schedule = scheduleRep.getReferenceById(id);
		if(schedule != null) scheduleRep.deleteById(schedule.getId());
	}
}
