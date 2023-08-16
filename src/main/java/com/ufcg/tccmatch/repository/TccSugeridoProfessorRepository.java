package com.ufcg.tccmatch.repository;

import com.ufcg.tccmatch.model.Professor;
import com.ufcg.tccmatch.model.TccReal;
import com.ufcg.tccmatch.model.TccSugeridoProfessor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TccSugeridoProfessorRepository extends AbstractRepository<TccSugeridoProfessor> {

    List<TccSugeridoProfessor> findAllByProfessorResponsavel(Professor professorResponsavel);

}
