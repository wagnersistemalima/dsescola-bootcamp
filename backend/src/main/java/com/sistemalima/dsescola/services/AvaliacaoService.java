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
	
	// metodo para buscar titulo pelo nome
	@Transactional(readOnly = true)
	public List<AvaliacaoDTO> findByTitulo(String titulo) {
		List<Avaliacao> list = repository.findByTitulo(titulo);
		return list.stream().map(x -> new AvaliacaoDTO(x)).collect(Collectors.toList());
	}
	
	// metodo para inserir uma avaliacao
	
	@Transactional
	public AvaliacaoDTO insert(AvaliacaoDTO dto) {
		Avaliacao entity = new Avaliacao();
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setFormulario(dto.getFormulario());
		entity = repository.save(entity);
		return new AvaliacaoDTO(entity);
	}
	
	// metodo atualizar
	
	

}
