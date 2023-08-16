package com.ufcg.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Coordenador extends AbstractEntity implements Notificavel {
	
	private String nome;
	
	private String email;

	@OneToOne
	private Usuario usuario;
	
	public Coordenador() {}
	
	public Coordenador(String nome, String email, Usuario usuario) {
		this.nome = nome;
		this.email = email;
	}
    
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public void notificar(String message) {
		System.out.println("Email enviado para: " + getEmail() + "\nConteúdo: " + message);
	}
}
