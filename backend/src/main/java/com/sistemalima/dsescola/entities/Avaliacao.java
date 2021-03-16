package com.sistemalima.dsescola.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao implements Serializable{
	
	// para que o objeto seja transformado em bytes
	
	private static final long serialVersionUID = 1L;
	// atributos basicos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	
	//associação para formulario / muitos para um / uma avaliação pode ter um formularios / um formulario pode estar em varias ava
	
	@ManyToOne
	private Formulario formulario;
	
	
	// associação para aluno / muitos para muitos
	
	@ManyToMany(mappedBy = "avaliacoes")
	private Set<Aluno> alunos = new HashSet<>();
	
	// construtor default
	
	public Avaliacao() {
		
	}
	
	// construtor personalizado
	

	public Avaliacao(Long id, String titulo, String descricao) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Avaliacao(Long id, String titulo, String descricao, Formulario formulario) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.formulario = formulario;
	}

	// Getters & setters

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
	
	public Set<Aluno> getAlunos() {
		return alunos;
	}
	
	// HashCode & equals


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
