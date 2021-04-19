package com.sistemalima.dsescola.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistemalima.dsescola.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	
	// metodo para resolver problema da n+1 consultas com consulta jpql
		
	@Query("SELECT obj FROM Aluno obj JOIN FETCH obj.avaliacoes WHERE obj IN :alunos")
	List<Aluno> findAlunosAvaliacoes(List<Aluno> alunos);
	
	// buscar query select aluno por nome
	
	Optional<Aluno> findByNome(String nome);
	
	
	//  metodo para buscar no banco de dados os emails dos alunos
	
	Aluno findByEmail(String email);
		
	

	


	

}
