package com.sistemalima.dsescola.services;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemalima.dsescola.dto.AlunoDTO;
import com.sistemalima.dsescola.dto.AlunoDTODetalhes;
import com.sistemalima.dsescola.entities.Aluno;
import com.sistemalima.dsescola.repositories.AlunoRepository;
import com.sistemalima.dsescola.services.exceptions.DataBaseException;
import com.sistemalima.dsescola.services.exceptions.ResourceNotFoundException;

@Service
public class AlunoService {
	
	// injeção de dependencia para a camada de acesso a dados
	
	
	@Autowired
	private AlunoRepository repository;
	
	// metodo buscar todos
	
	@Transactional(readOnly = true)
	public Page<AlunoDTO> findAllPaged(PageRequest pageRequest) {
		Page<Aluno> list = repository.findAll(pageRequest);
		repository.findAlunosAvaliacoes(list.stream().collect(Collectors.toList()));
		return list.map(x -> new AlunoDTO(x));
	}
	
	// metodo buscar aluno por id e suas avaliaçoes
	
	@Transactional
	public AlunoDTODetalhes findById(Long id) {
		Optional<Aluno> obj = repository.findById(id);
		Aluno entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new AlunoDTODetalhes(entity, entity.getAvaliacoes());
		
	}
	
	// metodo para buscar aluno pelo nome
	
	@Transactional
	public AlunoDTODetalhes findByNome(String nome) {
		Optional<Aluno> obj  = repository.findByNome(nome);
		Aluno entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new AlunoDTODetalhes(entity, entity.getAvaliacoes());
	}
	
	// metodo para inserir aluno
	
	@Transactional
	public AlunoDTO insert(AlunoDTO dto) {
		Aluno entity = new Aluno();
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setIdade(dto.getIdade());
		entity = repository.save(entity);
		return new AlunoDTO(entity);
	}
	
	// metodo para atualizar um aluno
	
	@Transactional
	public @Valid AlunoDTO update(Long id, @Valid AlunoDTO dto) {
		try {
			Aluno entity = repository.getOne(id);
			entity.setNome(dto.getNome());
			entity.setEmail(dto.getEmail());
			entity.setIdade(dto.getIdade());
			entity = repository.save(entity);
			return new AlunoDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado" + id);
		}
		
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado" + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de integridade");
		}
		
	}
	
	

}
