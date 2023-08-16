package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.Role;

public interface RoleService extends AbstractService<Role> {

    public Role getByName(String name) throws AppException;

}
