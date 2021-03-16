package com.sistemalima.dsescola.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistemalima.dsescola.dto.AlunoDTO;
import com.sistemalima.dsescola.dto.AlunoDTODetalhes;
import com.sistemalima.dsescola.services.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoResource {
	
	// injeção para a camada de serviço
	
	@Autowired
	private AlunoService service;
	
	// 1 end point / buscar todos retornando resposta 200 ok
	
	@GetMapping
	@Cacheable(value = "listaDeAlunos")
	public ResponseEntity<Page<AlunoDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
			) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<AlunoDTO> list = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	
	// 2 end point / buscar por id
	
	@GetMapping(value = "/{id}")
	@Cacheable(value = "alunoDetalhe")
	public ResponseEntity<AlunoDTODetalhes> findById(@PathVariable Long id) {
		AlunoDTODetalhes dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	// 3 end point / buscar por nome
	
	@GetMapping(value = "/nome")
	public ResponseEntity<List<AlunoDTODetalhes>> findByNome(String nome) {
		List<AlunoDTODetalhes> list = service.findByNome(nome);
		return ResponseEntity.ok().body(list);
	}
	
	// 4º end point inserir aluno
	
	@PostMapping
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	public ResponseEntity<AlunoDTO> insert(@RequestBody @Valid AlunoDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	
	// 5º end point atualizar um aluno
	
	@PutMapping(value = "/{id}")
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @Valid @RequestBody AlunoDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	// 6 end point deletar um aluno
	
	@DeleteMapping(value = "/{id}")
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	public ResponseEntity<AlunoDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
