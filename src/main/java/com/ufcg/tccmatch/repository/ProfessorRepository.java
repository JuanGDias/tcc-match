package com.ufcg.tccmatch.repository;

import java.util.List;
import java.util.Optional;

import com.ufcg.tccmatch.model.AreaEstudo;
import com.ufcg.tccmatch.model.Professor;
import com.ufcg.tccmatch.model.Usuario;

public interface ProfessorRepository extends AbstractRepository<Professor>{

	Optional<Professor> findByNome(String nomeProfessor);

	Optional<Professor> findByUsuario(Usuario usuario);

	List<Professor> findAllByAreasDeEstudo(AreaEstudo areaEstudo);
}
