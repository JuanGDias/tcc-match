package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.AbstractEntity;
import com.ufcg.tccmatch.model.Role;
import com.ufcg.tccmatch.model.Usuario;
import com.ufcg.tccmatch.repository.AbstractRepository;
import com.ufcg.tccmatch.repository.RoleRepository;
import com.ufcg.tccmatch.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public abstract class AbstractServiceImpl <E extends AbstractEntity, R extends AbstractRepository<E>> implements AbstractService<E> {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected PasswordEncoder encoder;

    public abstract R getRepository();

    public abstract String getEntityName();

    public E getById(Long id) throws AppException {
        Optional<E> entityOpt = getRepository().findById(id);
        if(entityOpt.isPresent()){
            return entityOpt.get();
        } else {
            throw new AppException(String.format("Entidade '%s' com ID '%d' não encontrada.", getEntityName(), id), HttpStatus.NOT_FOUND);
        }
    }

    public List<E> getAll() {
        return getRepository().findAll();
    }

    public void remover(Long id) throws AppException {
        Optional<E> entityOpt = getRepository().findById(id);
        if(entityOpt.isPresent()){
            E entity = entityOpt.get();
            getRepository().delete(entity);
        } else {
            throw new AppException(String.format("Entidade '%s' com ID '%d' não encontrada.", getEntityName(), id), HttpStatus.NOT_FOUND);
        }
    }

    public E save(E entity) throws AppException {
        return getRepository().save(entity);
    }

    protected Usuario getCurrentUser(){
        Optional<SecurityContext> contextOptional = Optional.ofNullable(SecurityContextHolder.getContext());

        Usuario currentUser = null;

        if (contextOptional.isPresent()) {
            SecurityContext context = contextOptional.get();

            if (context.getAuthentication().isAuthenticated()) {
                String loginUser = null;
                if (User.class.isAssignableFrom(context.getAuthentication().getPrincipal().getClass())) {
                    loginUser = ((User) context.getAuthentication().getPrincipal()).getUsername();
                } else {
                    loginUser = (String) context.getAuthentication().getPrincipal();
                }

                if (loginUser == null) {
                    loginUser = "Unknown-User";
                }

                Optional<Usuario> user = usuarioRepository.findByUsername(loginUser);

                currentUser = user.orElse(null);
            }

        }

        return currentUser;
    }

    protected Role getRoleByName(String roleName) throws AppException {
        Optional<Role> role = roleRepository.findByName(roleName);

        if(role.isEmpty()) {
            throw new AppException(String.format("Cargo com nome '%s' não encontrada.", roleName), HttpStatus.NOT_FOUND);
        }

        return role.get();
    }
}
