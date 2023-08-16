package com.ufcg.tccmatch.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo",
        discriminatorType = DiscriminatorType.STRING)
public class TccAbstract extends AbstractEntity {

    protected String titulo;

    @ManyToOne
    protected AreaEstudo areaDeConhecimento;

    protected String descricao;

    @Column(insertable = false, updatable = false)
    private String tipo;

    public TccAbstract() {
    }

    public TccAbstract(String titulo, AreaEstudo areaDeConhecimento, String descricao) {
        this.titulo = titulo;
        this.areaDeConhecimento = areaDeConhecimento;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public AreaEstudo getAreaDeConhecimento() {
        return areaDeConhecimento;
    }

    public void setAreaDeConhecimento(AreaEstudo areaDeConhecimento) {
        this.areaDeConhecimento = areaDeConhecimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

}
