package com.sistemalima.dsescola.fabrica;

import com.sistemalima.dsescola.dto.AlunoDTO;
import com.sistemalima.dsescola.entities.Aluno;

public class FabricaDeAluno {
	
	public static Aluno criandoAluno() {
		return new Aluno(1L, "Juliete", "juliete@gmail.com", 32);
	}
	
	public static AlunoDTO criandoAlunoDTO() {
		return new AlunoDTO(criandoAluno());
	}

}
