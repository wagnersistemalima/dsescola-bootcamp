package com.sistemalima.dsescola.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemalima.dsescola.dto.FormularioDTO;
import com.sistemalima.dsescola.services.FormularioService;

@RestController
@RequestMapping(value = "/formularios")
public class FormularioResource {
	
	// injeção de dependencia para a camada de serviço
	
	@Autowired
	private FormularioService service;
	
	// 1º end point buscar todos
	
	
	@GetMapping
	public ResponseEntity<List<FormularioDTO>> findAll() {
		List<FormularioDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
