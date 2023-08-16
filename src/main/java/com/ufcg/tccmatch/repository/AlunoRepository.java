package com.ufcg.tccmatch.repository;

import java.util.List;
import java.util.Optional;

import com.ufcg.tccmatch.model.Aluno;
import com.ufcg.tccmatch.model.AreaEstudo;
import com.ufcg.tccmatch.model.Usuario;

public interface AlunoRepository extends AbstractRepository<Aluno>{

	Optional<Aluno> findByMatricula(String matricula);

	Optional<Aluno> findByUsuario(Usuario usuario);

	List<Aluno> findAllByAreasDeEstudo(AreaEstudo areaEstudo);

}
