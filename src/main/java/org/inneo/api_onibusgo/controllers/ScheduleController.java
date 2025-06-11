package org.inneo.api_onibusgo.controllers;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.inneo.api_onibusgo.domains.Schedule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.inneo.api_onibusgo.services.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/schedule")
@Tag(name = "Horários", description = "Endpoints da API de horários de ônibus")
public class ScheduleController {
	private ScheduleService scheduleService;
	
	public ScheduleController( ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
	
	@PostMapping()
	@Operation(summary = "Cadastra novos horários")
	public ResponseEntity<?> create(@Valid @RequestBody Schedule request) {
	    try {		
	        return ResponseEntity.ok(scheduleService.create(request));
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}
	
	@PutMapping()
	@Operation(summary = "Atualiza um horário pelo id")
	public ResponseEntity<?> update(@Valid @RequestBody Schedule request) {
	    try {		
	        return ResponseEntity.ok(scheduleService.update(request));
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}

	@GetMapping()
	@Operation(summary = "Listar todos os horários")
	public ResponseEntity<?> findAll() {
		try {
			List<Schedule> response = scheduleService.findAll();
			if (response.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(response);
			}
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@GetMapping("/findById")
	@Operation(summary = "Retorna um horários pelo id")
	public ResponseEntity<?> findById(@RequestParam(required = true) long id) {
		try {
			Schedule response = scheduleService.findById(id);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@GetMapping("/findByRota")
	@Operation(summary = "Listar todos os horários pelo id da rota")
	public ResponseEntity<?> findByRota(@RequestParam(required = true) long id) {
		try {
			List<Schedule> response = scheduleService.findByRota(id);
			if (response.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(response);
			}
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@DeleteMapping()
	@Operation(summary = "Delete um horário pelo id")
	public ResponseEntity<?> delete(@RequestParam(required = true) Long id) {
	    try {
	        scheduleService.delete(id);			
	        return ResponseEntity.ok("Deletado com sucesso!");
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}
}
