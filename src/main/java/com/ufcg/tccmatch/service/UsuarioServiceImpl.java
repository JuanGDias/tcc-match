package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.Usuario;
import com.ufcg.tccmatch.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl extends AbstractServiceImpl<Usuario, UsuarioRepository> implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public Usuario getByUsername(String username) throws AppException {
		Optional<Usuario> entityOpt = usuarioRepository.findByUsername(username);

		if(entityOpt.isPresent()) {
			return entityOpt.get();
		} else {
			throw new AppException(String.format("Entidade '%s' com Username '%s' não encontrada.", getEntityName(), username), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public UsuarioRepository getRepository() {
		return usuarioRepository;
	}

	@Override
	public String getEntityName() {
		return "Usuario";
	}

}
