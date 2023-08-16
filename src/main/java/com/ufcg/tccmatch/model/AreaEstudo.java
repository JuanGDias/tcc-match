package com.ufcg.tccmatch.model;

import javax.persistence.Entity;

@Entity
public class AreaEstudo extends AbstractEntity {
	
	private String nome;
	
	public AreaEstudo() {}
	
	public AreaEstudo(String nome) {
		this.nome = nome;
	}
    
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
