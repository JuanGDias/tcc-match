package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.AbstractEntity;

import java.util.List;

public interface AbstractService<E extends AbstractEntity> {

    public E getById(Long id) throws AppException;

    public E save(E entity) throws AppException;

    public void remover(Long id) throws AppException;

    public List<E> getAll() throws AppException;
}
