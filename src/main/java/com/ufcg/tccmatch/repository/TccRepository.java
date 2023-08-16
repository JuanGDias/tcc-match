package com.ufcg.tccmatch.repository;

import java.util.List;
import java.util.Optional;

import com.ufcg.tccmatch.model.TccReal;
import com.ufcg.tccmatch.model.TccAbstract;

import org.springframework.stereotype.Repository;

@Repository
public interface TccRepository extends AbstractRepository<TccAbstract> {

    Optional<TccReal> findBytitulo(String titulo);

    List<TccAbstract> findAllByTipo(String tipo);

}
