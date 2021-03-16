package com.sistemalima.dsescola.dto;

import java.io.Serializable;

import com.sistemalima.dsescola.entities.Avaliacao;
import com.sistemalima.dsescola.entities.Formulario;

public class AvaliacaoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	// atributos basicos
	
	private Long id;
	private String titulo;
	private String descricao;
	
	private Formulario formulario;
	
	
	
	// construtor default
	
	public AvaliacaoDTO() {
		
	}
	
	// construtor personalizado

	public AvaliacaoDTO(Long id, String titulo, String descricao, Formulario formulario) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.formulario = formulario;
	}
	
	// construtor personalizado recebendo uma entidade
	
	public AvaliacaoDTO(Avaliacao entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.descricao = entity.getDescricao();
		this.formulario = entity.getFormulario();
	}
	
	
	
	// getters & setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}
	
	

}
