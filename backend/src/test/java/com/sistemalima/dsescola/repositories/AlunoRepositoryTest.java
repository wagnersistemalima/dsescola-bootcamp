package com.sistemalima.dsescola.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.sistemalima.dsescola.entities.Aluno;
import com.sistemalima.dsescola.fabrica.FabricaDeAluno;

// testando o repository do aluno

@DataJpaTest
public class AlunoRepositoryTest {

	// dependencia

	@Autowired
	private AlunoRepository repository;

	private long existeId;
	
	private long naoExisteId;
	
	private long totalDeAluno;

	// roda antes de cada teste

	@BeforeEach
	void setUp() {
		existeId = 1L;
		naoExisteId = 1000L;
		totalDeAluno = 4L;
	}

	// 1 cenario deletando um aluno

	@Test
	void deveriaDeletarUmAlunoCasoOIdExista() {

		// logica aqui

		repository.deleteById(existeId);

		Optional<Aluno> resultado = repository.findById(existeId);

		// assertivas aqui

		assertFalse(resultado.isPresent()); // o objeto não tem que estar mais cadastrado

	}

	// 2 cenario lançar uma exceção quando tentar deletar um aluno pelo id que não
	// existe cadastrado

	@Test
	public void deveriaLancarExcecaoAoTentarDeletarAlunoInexistente() {

		// logica aqui


		// assertivas aqui
		
		assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(naoExisteId);
		});

	}
	
	// 3 cenario, inserindo um aluno com id null
	
	@Test
	public void inserindoUmAlunoComIdNuloDeveriaCadastrarIdAutoIncremental() {
		
		// logica
		
		Aluno entity = FabricaDeAluno.criandoAluno();
		entity.setId(null);
		
		entity = repository.save(entity);
		Optional<Aluno> resultado = repository.findById(entity.getId());
		
		// assertiva
		
		assertNotNull(entity.getId());
		assertEquals(totalDeAluno + 1L, entity.getId());
		assertTrue(resultado.isPresent());
		
	}

}
