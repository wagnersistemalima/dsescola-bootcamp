package com.sistemalima.dsescola.dto;

import java.io.Serializable;

import com.sistemalima.dsescola.entities.Formulario;

public class FormularioDTO implements Serializable{

	// para que o objeto seja transformado em bytes
	
	private static final long serialVersionUID = 1L;
	
	// atributos basicos

	private Long id;
	private Integer nota;

	// construtor default

	public FormularioDTO() {

	}
	
	// construtor personalizado

	public FormularioDTO(Long id, Integer nota) {
		super();
		this.id = id;
		this.nota = nota;
	}

	// construtor personalizado recevendo uma entidade

	public FormularioDTO(Formulario entity) {
		this.id = entity.getId();
		this.nota = entity.getNota();
	}
	
	// Getters & setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}
	
	

}
