package com.ufcg.tccmatch.repository;

import com.ufcg.tccmatch.model.Role;

import java.util.Optional;

public interface RoleRepository extends AbstractRepository<Role>{

    Optional<Role> findByName(String name);

}
