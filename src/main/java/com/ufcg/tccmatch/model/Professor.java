package com.ufcg.tccmatch.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Professor extends AbstractEntity implements Notificavel {

    private String nome;

    private String email;

    @ElementCollection(fetch=FetchType.LAZY)
    private List<String> laboratorios;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<AreaEstudo> areasDeEstudo;

    private int QuotaDeDisponibilidade = 0;

    @OneToOne
    private Usuario usuario;

    public Professor() {}
    
    public Professor(String nome, String email, List<String> laboratorios, Usuario usuario) {
        this.nome = nome;
        this.email = email;
        this.laboratorios = laboratorios;
        this.areasDeEstudo = new ArrayList<AreaEstudo>();
        this.usuario = usuario;
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
    
    public List<String> getLaboratorios() {
        return this.laboratorios;
    }
    
    public void setLaboratorios(List<String> laboratorios) {
    	this.laboratorios = laboratorios;
    }

    public List<AreaEstudo> getAreasDeEstudo() {
        return areasDeEstudo;
    }

    public void setAreasDeEstudo(List<AreaEstudo> areasDeEstudo) {
        this.areasDeEstudo = areasDeEstudo;
    }

    public int getQuotaDeDisponibilidade(){return  this.QuotaDeDisponibilidade;}

    public void setQuotaDeDisponibilidade(int quotaDeDisponibilidade){this.QuotaDeDisponibilidade = quotaDeDisponibilidade;}

    public void addQuotaDeDisponibilidade(){this.QuotaDeDisponibilidade += 1;}

    public void substractQuotaDeDisponibilidade(){this.QuotaDeDisponibilidade -= 1;}

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
