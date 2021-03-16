package com.sistemalima.dsescola.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.sistemalima.dsescola.dto.UserUpdateDTO;
import com.sistemalima.dsescola.entities.Aluno;
import com.sistemalima.dsescola.repositories.AlunoRepository;
import com.sistemalima.dsescola.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

	// injeção de dependência para HTTPServeletRequest

	@Autowired
	private HttpServletRequest request;
	
	// injeção de dependencia para a camada de acesso a dados
	
	@Autowired
	private AlunoRepository repository;

	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
		
		// guardar na variavel uriVar o dicionario com os atributos da url
		
		@SuppressWarnings("unchecked")
		var uriVar = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userId = Long.parseLong(uriVar.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		
		Aluno aluno = repository.findByEmail(dto.getEmail());
		
		if (aluno != null && userId != aluno.getId()) {
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