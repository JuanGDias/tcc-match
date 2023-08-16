package com.ufcg.tccmatch.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("SUGERIDO-PROFESSOR")
public class TccSugeridoProfessor extends TccAbstract {

    @ManyToOne
    private Professor professorResponsavel;

    public TccSugeridoProfessor() {
        super();
    }

    public TccSugeridoProfessor(String titulo, AreaEstudo areaDeConhecimento, String descricao, Professor professor) {
        super(titulo, areaDeConhecimento, descricao);
        this.professorResponsavel = professor;
    }

    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(Professor professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }
    
}
