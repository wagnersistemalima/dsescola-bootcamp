package com.sistemalima.dsescola.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sistemalima.dsescola.entities.Aluno;
import com.sistemalima.dsescola.entities.Avaliacao;

public class AlunoDTODetalhes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	
	
	// lista vazia de avaliaçoes 
	
	private List<AvaliacaoDTO> avaliacoes = new ArrayList<>();
	
	public AlunoDTODetalhes() {
		
	}
	
	// construtor personalizado
	
	public AlunoDTODetalhes(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	// construtor recebendo uma entidade
	
	public AlunoDTODetalhes(Aluno entity) {
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		
	}
	
	// construtor recebendo um entidade e uma lista de avaliacoes
	
	public AlunoDTODetalhes(Aluno entity, Set<Avaliacao> avaliacoes) {
		this(entity);
		avaliacoes.forEach(x -> this.avaliacoes.add(new AvaliacaoDTO(x)));
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

	public List<AvaliacaoDTO> getAvaliacoes() {
		return avaliacoes;
	}
	
	
}
