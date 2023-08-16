package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.model.Coordenador;
import com.ufcg.tccmatch.model.Professor;
import com.ufcg.tccmatch.model.Report;

import java.util.List;
import java.util.Map;

public interface CoordenadorService extends AbstractService<Coordenador> {

    Map<String, Map<String, List<Report>>> gerarRelatorioProblemas();
}
