package com.ufcg.tccmatch.service;

import java.util.List;

import com.ufcg.tccmatch.dto.AvaliacaoDePedidoDeOrientacaoDTO;
import com.ufcg.tccmatch.dto.TccDTO;
import com.ufcg.tccmatch.dto.TccRealDTO;
import com.ufcg.tccmatch.dto.TccSugeridoDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.*;

public interface TccService extends AbstractService<TccAbstract> {

    List<TccAbstract> obterTodosPorTipo(String tipo) throws AppException;

    TccReal obterTccOficialPorId(Long id)  throws AppException;

    TccSugeridoAluno obterTccSugeridoPorId(Long id) throws AppException;

    TccAbstract obterTccGeralPorId(Long id) throws AppException;

    List<TccSugeridoProfessor> obterTccsPorProfessor() throws AppException;

    TccSugeridoProfessor adicionaTcc(TccDTO tccTDto) throws AppException;

    TccSugeridoAluno adicionaTccSugerido(TccSugeridoDTO tccTDto) throws AppException;

    PedidoDeOrientacao adicionarSolicitacaoAluno(Long id) throws AppException;

    void manifestarInteresse(Long id) throws AppException;

    List<PedidoDeOrientacao> getAllPedidosByProfessor() throws AppException;

    void avaliarPedidoDeOrientacao(AvaliacaoDePedidoDeOrientacaoDTO avaliacao) throws AppException;

    TccReal cadastrarTccReal(TccRealDTO tcc) throws AppException;

    TccReal finalizarOrientacao(long tccId, String periodo) throws AppException;

    List<TccReal> listarTccCadastradoPeriodo(String periodo) throws AppException;

    List<TccReal> listarTccFinalizadoPeriodo(String periodo) throws AppException;
}
