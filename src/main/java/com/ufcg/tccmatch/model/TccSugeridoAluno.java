package com.ufcg.tccmatch.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("SUGERIDO-ALUNO")
public class TccSugeridoAluno extends TccAbstract{

    @ManyToOne
    private Aluno alunoSugestor;

    public TccSugeridoAluno() {
        super();
    }

    public TccSugeridoAluno(String titulo, AreaEstudo areaDeConhecimento, String descricao, Aluno aluno) {
        super(titulo, areaDeConhecimento, descricao);
        this.alunoSugestor = aluno;
    }

    public Aluno getAlunoSugestor() {
        return alunoSugestor;
    }

    public void setAlunoSugestor(Aluno alunoSugestor) {
        this.alunoSugestor = alunoSugestor;
    }
    
}
