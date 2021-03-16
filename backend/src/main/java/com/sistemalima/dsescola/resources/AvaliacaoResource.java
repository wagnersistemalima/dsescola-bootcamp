package com.sistemalima.dsescola.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemalima.dsescola.dto.AvaliacaoDTO;
import com.sistemalima.dsescola.services.AvaliacaoService;

@RestController
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoResource {
	
	// injeção de dependencia para a camada de serviço
	
	@Autowired
	private AvaliacaoService service;
	
	// 1º end point / buscar todos retornando uma resposta 200 ok
	
	@GetMapping
	public ResponseEntity<List<AvaliacaoDTO>> findAll() {
		List<AvaliacaoDTO> list = service.findAll();	
		return ResponseEntity.ok().body(list);
	}

}
