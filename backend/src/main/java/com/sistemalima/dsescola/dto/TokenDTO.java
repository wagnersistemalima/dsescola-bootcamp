package com.sistemalima.dsescola.dto;

import java.io.Serializable;

// objeto para trafegar o token

public class TokenDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// atributos basicos
	
	private String token;
	private String tipo;
	
	// construtor default
	
	public TokenDTO() {
		
	}
	
	// construtor personalizado

	public TokenDTO(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}
	
	// getters & settes

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
