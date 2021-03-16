package com.sistemalima.dsescola.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sistemalima.dsescola.dto.UserInsertDTO;
import com.sistemalima.dsescola.entities.Aluno;
import com.sistemalima.dsescola.repositories.AlunoRepository;
import com.sistemalima.dsescola.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
	
	// injeção de dependencia para a camada de acesso a dados AlunoRepository
	
	@Autowired
	private AlunoRepository repository;

	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		
		Aluno aluno = repository.findByEmail(dto.getEmail());
		
		
		if (aluno != null) {
			list.add(new FieldMessage("email", "Este email já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}



