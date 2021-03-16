package com.sistemalima.dsescola.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemalima.dsescola.dto.FormularioDTO;
import com.sistemalima.dsescola.entities.Formulario;
import com.sistemalima.dsescola.repositories.FormularioRepository;

@Service
public class FormularioService {
	
	// injeção de dependencia para a camada de acesso a dados
	
	
	@Autowired
	private FormularioRepository repository;
	
	// metodo buscar todos
	
	@Transactional
	public List<FormularioDTO> findAll() {
		List<Formulario> list = repository.findAll();
		return list.stream().map(x -> new FormularioDTO(x)).collect(Collectors.toList());
	}

}
