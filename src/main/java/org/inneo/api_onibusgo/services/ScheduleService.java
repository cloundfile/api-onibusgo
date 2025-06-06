package org.inneo.api_onibusgo.services;

import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;
import org.inneo.api_onibusgo.domains.Schedule;

import org.inneo.api_onibusgo.ferramentas.utils;
import org.inneo.api_onibusgo.specs.ScheduleSpec;
import org.inneo.api_onibusgo.repositories.ScheduleRep;

import org.inneo.api_onibusgo.configuration.MessageException;

@Service 
@Transactional
public class ScheduleService {
	private final ScheduleRep scheduleRep;
	
	public ScheduleService(ScheduleRep scheduleRep) {
        this.scheduleRep = scheduleRep;
    }
	
	public Schedule create(Schedule request) {		
	    return scheduleRep.saveAndFlush(request);
	}
	
	public Schedule update(Schedule request) {	
		Schedule schedule = scheduleRep.getReferenceById(request.getId());		
		if(schedule == null) schedule = new Schedule();
		BeanUtils.copyProperties(request, schedule);
	    return scheduleRep.saveAndFlush(schedule);
	}
	
	public List<Schedule> findAll() {
		if(utils.isWeekend()) throw new MessageException("Horários de transporte não estão disponíveis durante os finais de semana.");
		return scheduleRep.findAll();
	}
	
	public List<Schedule> findBy(String codigo) {
		List<Schedule> response = scheduleRep.findAll(ScheduleSpec.doFilter(codigo));
		return response;
	}
	
	public void delete(Long id) {	
		Schedule schedule = scheduleRep.getReferenceById(id);
		if(schedule != null) scheduleRep.deleteById(schedule.getId());
	}
}
