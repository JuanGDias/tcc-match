package com.ufcg.tccmatch.dto;

import com.ufcg.tccmatch.model.Aluno;

public class TccSugeridoDTO extends TccAbstractDTO {

    private Aluno alunoSugestor;

    public Aluno getAlunoSugestor() {
        return alunoSugestor;
    }

    public void setAlunoSugestor(Aluno alunoSugestor) {
        this.alunoSugestor = alunoSugestor;
    }

}
