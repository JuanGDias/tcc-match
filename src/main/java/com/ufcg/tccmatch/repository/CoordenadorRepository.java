package com.ufcg.tccmatch.repository;

import com.ufcg.tccmatch.model.Aluno;
import com.ufcg.tccmatch.model.Coordenador;
import com.ufcg.tccmatch.model.Usuario;

import java.util.Optional;

public interface CoordenadorRepository extends AbstractRepository<Coordenador>{

    Optional<Coordenador> findByUsuario(Usuario usuario);

}
