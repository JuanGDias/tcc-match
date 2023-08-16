package com.ufcg.tccmatch.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("REAL")
public class TccReal extends TccAbstract {

    @ManyToOne
    private Professor orientador;

    @ManyToOne
    private Aluno aluno;

    private String periodoInicial;

    private String periodoFinal;

    @Enumerated(EnumType.STRING)
    private StatusOrientacao status;

    public TccReal() {
        super();
    }

    public TccReal(String titulo, AreaEstudo areaDeConhecimento, String descricao, Professor orientador, Aluno aluno, String periodoInicial, String periodoFinal, StatusOrientacao status) {
        super(titulo, areaDeConhecimento, descricao);
        this.orientador = orientador;
        this.aluno = aluno;
        this.periodoInicial = periodoInicial;
        this.periodoFinal = periodoFinal;
        this.status = status;
    }

    public Professor getOrientador() {
        return orientador;
    }

    public void setOrientador(Professor orientador) {
        this.orientador = orientador;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(String periodo) {
        this.periodoInicial = periodo;
    }

    public String getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(String periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public StatusOrientacao getStatus() {
        return status;
    }

    public void setStatus(StatusOrientacao status) {
        this.status = status;
    }
}
