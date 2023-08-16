package com.ufcg.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PedidoDeOrientacao extends AbstractEntity {

    @ManyToOne(targetEntity = Aluno.class)
    private Aluno aluno;

    @ManyToOne(targetEntity = Aluno.class)
    private Professor professor;

    @ManyToOne(targetEntity = TccReal.class)
    private TccSugeridoProfessor tcc;

    public PedidoDeOrientacao() {}

    public PedidoDeOrientacao(Aluno aluno, Professor professor, TccSugeridoProfessor tcc) {
        this.aluno = aluno;
        this.professor = professor;
        this.tcc = tcc;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public TccSugeridoProfessor getTcc() {
        return tcc;
    }

    public void setTcc(TccSugeridoProfessor tcc) {
        this.tcc = tcc;
    }

}
