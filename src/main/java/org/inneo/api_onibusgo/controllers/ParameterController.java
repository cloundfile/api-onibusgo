package org.inneo.api_onibusgo.controllers;

import java.util.List;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import org.inneo.api_onibusgo.domains.Parameter;
import org.inneo.api_onibusgo.services.ParameterService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/v1/parametro")
@Tag(name = "Parâmetros", description = "Endpoints da API de parâmetros")
public class ParameterController {
	private final ParameterService parameterService;
	
	@PostMapping()
	@Operation(summary = "Cadastra novos parâmetro")
	public ResponseEntity<?> create(@Valid @RequestBody Parameter request) {
	    try {		
	        return ResponseEntity.ok(parameterService.create(request));
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}
	
	@PutMapping()
	@Operation(summary = "Atualiza um horário pelo id")
	public ResponseEntity<?> update(@Valid @RequestBody Parameter request) {
	    try {		
	        return ResponseEntity.ok(parameterService.update(request));
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}

	@GetMapping()
	@Operation(summary = "Listar todos os parâmetro")
	public ResponseEntity<?> findAll() {
		try {
			List<Parameter> response = parameterService.findAll();
			if (response.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(response);
			}
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@GetMapping("/findbyId")
	@Operation(summary = "Listar todos os parâmetro id ou codigo")
	public ResponseEntity<?> findById(
			@RequestParam(required = true) String page,
			@RequestParam(required = true) String field) {
		try {
			List<Parameter> response = parameterService.findBy(page, field);
			if (response.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(response);
			}
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@GetMapping("/findby")
	@Operation(summary = "Retorna um parâmetro pelo id")
	public ResponseEntity<?> findBy(@RequestParam(required = true) Long id) {
		try {
			Parameter response = parameterService.findById(id);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@DeleteMapping()
	@Operation(summary = "Delete um parâmetro pelo id")
	public ResponseEntity<?> delete(@RequestParam(required = true) Long id) {
	    try {
	    	parameterService.delete(id);			
	        return ResponseEntity.ok("Deletado com sucesso!");
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}
}
