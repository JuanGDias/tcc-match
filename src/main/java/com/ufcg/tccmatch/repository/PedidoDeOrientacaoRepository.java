package com.ufcg.tccmatch.repository;

import com.ufcg.tccmatch.model.PedidoDeOrientacao;
import com.ufcg.tccmatch.model.Professor;

import java.util.List;

public interface PedidoDeOrientacaoRepository extends AbstractRepository<PedidoDeOrientacao>{

    List<PedidoDeOrientacao> findAllByProfessor(Professor professor);
}
