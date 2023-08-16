package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.Usuario;

public interface UsuarioService extends AbstractService<Usuario> {

    public Usuario getByUsername(String username) throws AppException;

}
