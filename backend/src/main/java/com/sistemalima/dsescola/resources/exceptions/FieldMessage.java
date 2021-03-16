package com.sistemalima.dsescola.resources.exceptions;

import java.io.Serializable;

// classe auxiliar para carregar o nome dos campos  e as mensagens de respostas dos
// dados que nao forem validados

public class FieldMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String fieldName;            
	private String message;

	// construtor default
	
	public FieldMessage() {
		
	}
	
	// construtor personalizado

	public FieldMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}
	
	// getters & setters

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
