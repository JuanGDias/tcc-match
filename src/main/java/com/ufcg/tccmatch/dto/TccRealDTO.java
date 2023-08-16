package com.ufcg.tccmatch.dto;

import com.ufcg.tccmatch.model.Aluno;
import com.ufcg.tccmatch.model.Professor;

public class TccRealDTO extends TccAbstractDTO{

    private Professor orientador;

    private Aluno aluno;

    private String periodo;

    public Professor getOrientador() {
        return orientador;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getPeriodo() {
        return periodo;
    }
}
