package com.sistemalima.dsescola.dto;

import java.io.Serializable;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

// objeto para trafegar dados que chega do formulario cliente

public class LoginDTOForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// atributos basicos
	
	private String email;
	private String senha;
	
	// construtor default
	
	public LoginDTOForm() {
		
	}
	
	// construtor com argumentos

	public LoginDTOForm(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	// getters & setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	// metodo para converter

	public UsernamePasswordAuthenticationToken converter() {	
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
	
}
