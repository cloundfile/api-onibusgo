package org.inneo.api_onibusgo.repositories;

import java.util.List;

import org.inneo.api_onibusgo.domains.Schedule;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ScheduleRep extends JpaRepository<Schedule, Long>, JpaSpecificationExecutor<Schedule>{
	List<Schedule> findByCodigo(String codigo);
}