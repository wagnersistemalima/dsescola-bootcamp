package com.sistemalima.dsescola.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_aluno")
public class Aluno implements Serializable{
	
	// para o objeto ser transformado em bytes
	
	private static final long serialVersionUID = 1L;
	// atributos basicos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Column(unique = true)
	private String email;
	private Integer idade;
	
	
	// associação de muitos para muitos
	
	@ManyToMany
	@JoinTable(name = "tb_resposta", joinColumns = @JoinColumn (name = "id_aluno"), inverseJoinColumns = @JoinColumn (name = "id_avaliacao"))
	private Set<Avaliacao> avaliacoes = new HashSet<>();
	
	// construtor default
	
	public Aluno() {
		
	}
	
	// construtor personalizado

	public Aluno(Long id, String nome, String email, Integer idade) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.idade = idade;
	}
	
	// Getters & setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	

	public Set<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}
