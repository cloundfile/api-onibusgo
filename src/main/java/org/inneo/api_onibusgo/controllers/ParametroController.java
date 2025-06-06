package org.inneo.api_onibusgo.controllers;

import java.util.List;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.inneo.api_onibusgo.domains.Parametro;

import org.inneo.api_onibusgo.services.ParametroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/parametro")
@Tag(name = "Parâmetros", description = "Endpoints da API de parâmetros")
public class ParametroController {
	private ParametroService parametroService;
	
	public ParametroController( ParametroService parametroService ) {
        this.parametroService = parametroService;
    }
	
	@PostMapping()
	@Operation(summary = "Cadastra novos parâmetro")
	public ResponseEntity<?> create(@Valid @RequestBody Parametro parametro) {
	    try {		
	        return ResponseEntity.ok(parametroService.create(parametro));
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}
	
	@PutMapping()
	@Operation(summary = "Atualiza um horário pelo id")
	public ResponseEntity<?> update(@Valid @RequestBody Parametro parametro) {
	    try {		
	        return ResponseEntity.ok(parametroService.update(parametro));
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}

	@GetMapping()
	@Operation(summary = "Listar todos os parâmetro")
	public ResponseEntity<?> findAll() {
		try {
			List<Parametro> response = parametroService.findAll();
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
	@Operation(summary = "Listar todos os parâmetro id ou codigo")
	public ResponseEntity<?> findBy(@RequestParam(required = true) String codigo) {
		try {
			List<Parametro> response = parametroService.findBy(codigo);
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
	@Operation(summary = "Delete um parâmetro pelo id")
	public ResponseEntity<?> delete(@RequestParam(required = true) Long id) {
	    try {
	    	parametroService.delete(id);			
	        return ResponseEntity.ok("Deletado com sucesso!");
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}
}
