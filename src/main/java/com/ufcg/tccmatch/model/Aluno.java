package com.ufcg.tccmatch.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Aluno extends AbstractEntity implements Notificavel {
	
    private String nome;

    //Alterar tipo quando for decidido
    private String matricula;

    private String email;

    //Alterar tipo quando for decidido
    private String periodoPrevistoParaConclusao;

    @OneToOne
    private Usuario usuario;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<AreaEstudo> areasDeEstudo;

    private String propostaTemaTCC;

    public Aluno() {

    }

    public Aluno(String nome, String matricula, String email, String periodoPrevistoParaConclusao, Usuario usuario) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.periodoPrevistoParaConclusao = periodoPrevistoParaConclusao;
        this.usuario = usuario;
        this.areasDeEstudo = new ArrayList<AreaEstudo>();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPeriodoPrevistoParaConclusao() {
        return this.periodoPrevistoParaConclusao;
    }

    public void setPeriodoPrevistoParaConclusao(String periodoPrevistoParaConclusao) {
        this.periodoPrevistoParaConclusao = periodoPrevistoParaConclusao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPropostaTemaTCC() {
        return propostaTemaTCC;
    }

    public void setPropostaTemaTCC(String propostaTemaTCC) {
        this.propostaTemaTCC = propostaTemaTCC;
    }

	public List<AreaEstudo> getAreasDeEstudo() {
	    return this.areasDeEstudo;
	}

	public void setAreasDeEstudo(List<AreaEstudo> areasDeEstudo) {
	    this.areasDeEstudo = areasDeEstudo;
	}
	
	public void adicionarAreaEstudo(AreaEstudo areaEstudo) {
		this.areasDeEstudo.add(areaEstudo);
	}

    @Override
    public void notificar(String message) {
        System.out.println("Email enviado para: " + getEmail() + "\nConteúdo: " + message);
    }
}
