package com.ufcg.tccmatch.repository;

import com.ufcg.tccmatch.model.Professor;
import com.ufcg.tccmatch.model.StatusOrientacao;
import com.ufcg.tccmatch.model.TccReal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TccRealRepository extends AbstractRepository<TccReal> {

    List<TccReal> findAllByPeriodoInicial(String periodo);

    List<TccReal> findAllByPeriodoFinal(String periodo);

    List<TccReal> findAllByOrientadorAndStatus(Professor professor, StatusOrientacao status);

}
