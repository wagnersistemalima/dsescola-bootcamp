package com.sistemalima.dsescola.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.sistemalima.dsescola.entities.Aluno;
import com.sistemalima.dsescola.entities.Avaliacao;

public class AlunoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	// atributos basicos
	
	private Long id;
	
	@Size(max = 30, message = "O campo deve ter no maximo 30 caracteres")
	private String nome;
	
	@Email(message = "Favor entrar com um email válido")
	@Size(max = 30, message = "O campo deve ter no maximo 30 caracteres")
	private String email;
	
	@Min(value = 18, message = "A idade precisa ser maior ou igual a 18 anos")
	private Integer idade;
	
	private List<AvaliacaoDTO> avaliacoes = new ArrayList<>();
	
	
	// construtor default
	

	public AlunoDTO() {
		
	}
	
	// construtor personalizado recebendo nome, email
	
	public AlunoDTO(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	// construtor personalizado
	
	public AlunoDTO(Long id, String nome, String email, Integer idade, List<AvaliacaoDTO> avaliacoes) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.idade = idade;
		this.avaliacoes = avaliacoes;
	}

	// construtor personalizado recebendo uma entidade
	
	public AlunoDTO(Aluno entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.idade = entity.getIdade();
		this.avaliacoes = entity.getAvaliacoes().stream().map(x -> new AvaliacaoDTO(x)).collect(Collectors.toList());
	}

	// construtor personalizado
	
	public AlunoDTO(Aluno entity, Set<Avaliacao> avaliacoes) {
		this(entity);
		avaliacoes.forEach(x -> this.avaliacoes.add(new AvaliacaoDTO(x)));
	}
	
	// getters & setters

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
	
	
	public List<AvaliacaoDTO> getAvaliacoes() {
		return avaliacoes;
	}
}
