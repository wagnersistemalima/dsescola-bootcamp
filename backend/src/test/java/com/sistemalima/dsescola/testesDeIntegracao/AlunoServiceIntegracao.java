package com.sistemalima.dsescola.testesDeIntegracao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sistemalima.dsescola.services.AlunoService;
import com.sistemalima.dsescola.services.exceptions.ResourceNotFoundException;

// carregar o contexto @SpringBootTest /// teste de integração 

@Transactional
@SpringBootTest
public class AlunoServiceIntegracao {

	// Injeção de dependencia

	@Autowired
	private AlunoService service;

	// dependencia

	private long idExiste;
	private long idNaoExiste;

	@BeforeEach
	public void setUp() throws Exception {
		idExiste = 1L;
		idNaoExiste = 1000L;
		
	}

	// 1 ceneario, deletar um aluno que existe pelo id, retornar nada

	@Test
	public void deletandoIdQueExiste() {

		// assertiva, nao vai lançar nenhuma exception

		assertDoesNotThrow(() -> {
			service.delete(idExiste);
		});

		
	}

	// 2 cenario informando um id que não existe, que deve lançar uma exceção
	// ResourceNotFoundException

	@Test
	public void deletandoUmIdInexistente() {

		assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(idNaoExiste);
		});

	}


}
