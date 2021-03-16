package com.sistemalima.dsescola.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemalima.dsescola.dto.AvaliacaoDTO;
import com.sistemalima.dsescola.entities.Avaliacao;
import com.sistemalima.dsescola.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	
	// injeção de dependencia para a camada de acesso a dados
	
	
	@Autowired
	private AvaliacaoRepository repository;
	
	// metodo buscar todos
	
	@Transactional(readOnly = true)
	public List<AvaliacaoDTO> findAll() {
		List<Avaliacao> list = repository.findAll();
		return list.stream().map(x -> new AvaliacaoDTO(x)).collect(Collectors.toList());
		
	}
	
	

}
