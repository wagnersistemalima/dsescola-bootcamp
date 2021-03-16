package com.sistemalima.dsescola.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemalima.dsescola.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	// buscar query select aluno por nome
	
	List<Aluno> findByNome(String nome);

	


	

}
