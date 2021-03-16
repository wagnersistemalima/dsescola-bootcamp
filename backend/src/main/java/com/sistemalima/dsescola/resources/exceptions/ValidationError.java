package com.sistemalima.dsescola.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	// atributo basico uma lista para adicionar erros
	
	private List<FieldMessage> errors = new ArrayList<>();

	// Getters
		
	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	// metodo para adicionar erros
	
	public void addErro(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
