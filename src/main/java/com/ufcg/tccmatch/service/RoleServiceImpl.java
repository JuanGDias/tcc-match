package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.Role;
import com.ufcg.tccmatch.model.Usuario;
import com.ufcg.tccmatch.repository.RoleRepository;
import com.ufcg.tccmatch.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl extends AbstractServiceImpl<Role, RoleRepository> implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role getByName(String name) throws AppException {
		Optional<Role> entityOpt = roleRepository.findByName(name);

		if(entityOpt.isPresent()) {
			return entityOpt.get();
		} else {
			throw new AppException(String.format("Entidade '%s' com nome '%s' não encontrada.", getEntityName(), name), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public RoleRepository getRepository() {
		return roleRepository;
	}

	@Override
	public String getEntityName() {
		return "Role";
	}

}
