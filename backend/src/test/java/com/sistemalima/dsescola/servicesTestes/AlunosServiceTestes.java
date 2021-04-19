package com.sistemalima.dsescola.servicesTestes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sistemalima.dsescola.entities.Aluno;
import com.sistemalima.dsescola.fabrica.FabricaDeAluno;
import com.sistemalima.dsescola.repositories.AlunoRepository;
import com.sistemalima.dsescola.services.AlunoService;
import com.sistemalima.dsescola.services.exceptions.DataBaseException;
import com.sistemalima.dsescola.services.exceptions.ResourceNotFoundException;

// testes de unidade da classe service com mock

@ExtendWith(SpringExtension.class) 
public class AlunosServiceTestes {
	
	// classe a ser testada
	
	@InjectMocks
	private AlunoService service;
	
	// dependencia
	
	@Mock
	private AlunoRepository repository;
	
	private long idExiste;
	private long idNaoExiste;
	private long idDependente;
	private Aluno aluno;
	
	@BeforeEach
	public void setUp() throws Exception {
		idExiste = 1L;
		idNaoExiste = 1000L;
		idDependente = 4L;
		aluno = FabricaDeAluno.criandoAluno();
		
		// configuração do objeto mokado repository, para guando for chamado não fazer nada
		
		Mockito.doNothing().when(repository).deleteById(idExiste);
		
		// configuração do objeto repository , para o id que não existe
		
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(idNaoExiste);
		
		// configuração do objeto repository, para um id dependente, que tem associação
		
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(idDependente);
		
		// simulando o comportamento do findById, retornando o objeto
		
		Mockito.when(repository.findById(idExiste)).thenReturn(Optional.of(aluno));
		
		// simulando o comportamento do findById com id inexistente, retornando um optional vazio
		
		Mockito.when(repository.findById(idNaoExiste)).thenReturn(Optional.empty());
	}
	
	// 1 ceneario, deletar um aluno que existe pelo id, retornar nada
	
	@Test
	public void deletandoIdQueExiste() {
		
		// assertiva, nao vai lançar nenhuma exception
		
		assertDoesNotThrow(() -> {
			service.delete(idExiste);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(idExiste);
	}
		
		
	// 2 cenario informando um id que não existe, que deve lançar uma exceção	ResourceNotFoundException
	
	@Test
	public void deletandoUmIdInexistente() {
		
		assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(idNaoExiste);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(idNaoExiste);
	}
	
	// 3 cenario, que pode acontecer no futuro caso o sistema evolua,  informando um id dependente que 
	// não pode ser apagado, vai lançar uma exceção DataBaseException
	
	@Test
	public void deletandoUmIdQueEstaAssociadoComOutroObjeto() {
		
		assertThrows(DataBaseException.class, () -> {
			service.delete(idDependente);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(idDependente);
	}

}
