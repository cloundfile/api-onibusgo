package org.inneo.api_onibusgo.controllers;

import java.util.List;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.inneo.api_onibusgo.domains.Rota;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.inneo.api_onibusgo.services.RotaServices;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/rotas")
@AllArgsConstructor
@Tag(name = "Rotas", description = "Endpoints da API de rotas")
public class RotaController {
	private RotaServices rotaService;
	
	@PostMapping()
	@Operation(summary = "Cadastra novas rotas")
	public ResponseEntity<?> create(@Valid @RequestBody Rota request) {
	    try {		
	        return ResponseEntity.ok(rotaService.create(request));
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}
	
	@PutMapping()
	@Operation(summary = "Atualiza uma rota pelo id")
	public ResponseEntity<?> update(@Valid @RequestBody Rota request) {
	    try {		
	        return ResponseEntity.ok(rotaService.update(request));
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}

	@GetMapping()
	@Operation(summary = "Listar todas as rotas")
	public ResponseEntity<?> findAll() {
		try {
			List<Rota> response = rotaService.findAll();
			if (response.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(response);
			}
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@GetMapping("/find")
	@Operation(summary = "Busca uma rota pelo id")
	public ResponseEntity<?> findID(@RequestParam(required = true) Long id) {
		try {
			Rota response = rotaService.findID(id);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@DeleteMapping()
	@Operation(summary = "Delete uma rota pelo id")
	public ResponseEntity<?> delete(@RequestParam(required = true) Long id) {
	    try {
	    	rotaService.delete(id);			
	        return ResponseEntity.ok("Deletado com sucesso!");
	    } catch (Exception ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}
}
