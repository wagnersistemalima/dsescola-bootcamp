package com.sistemalima.dsescola.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sistemalima.dsescola.services.exceptions.DataBaseException;
import com.sistemalima.dsescola.services.exceptions.ResourceNotFoundException;

// Essa classe vai interceptar a exceção e fazer o tratamento adequado.
// Manipulador de Exceptions

@ControllerAdvice
public class ResourceExceptionHandller {

	// metodo para tratamento de erro recurso não encontrado / resposta 404
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("recurso não encontrado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

	// metodo para erro de validação

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validatiom(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Validation exception!");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			error.addErro(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(error);
	}

	// metodo para tratamento de erro violação de integridade / resposta 400
	// Guando tentar apagar um objeto que está associado com outro, e por questoes de integridade referencial
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value()); // erro 400
		error.setError("exceção de base de dados");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

}
