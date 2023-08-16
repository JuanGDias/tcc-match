package com.ufcg.tccmatch.repository;

import com.ufcg.tccmatch.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends AbstractRepository<Usuario>{

    Optional<Usuario> findByUsername(String username);

}
