package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.model.Coordenador;
import com.ufcg.tccmatch.model.Report;
import com.ufcg.tccmatch.repository.CoordenadorRepository;
import com.ufcg.tccmatch.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CoordenadorServiceImpl extends AbstractServiceImpl<Coordenador, CoordenadorRepository> implements CoordenadorService {

	@Autowired
	CoordenadorRepository coordenadorRepository;

	@Autowired
	ReportRepository reportRepository;

	@Override
	public Map<String, Map<String, List<Report>>> gerarRelatorioProblemas() {
		List<Report> reports = reportRepository.findAll();

		Map<String, Map<String, List<Report>>> result = new HashMap<>();

		for (Report report: reports) {
			if(!result.containsKey(report.getTcc().getPeriodoInicial())) {
				result.put(report.getTcc().getPeriodoInicial(), new HashMap<>());
				result.get(report.getTcc().getPeriodoInicial()).put("TEACHER", new ArrayList<>());
				result.get(report.getTcc().getPeriodoInicial()).put("STUDENT", new ArrayList<>());
			}

			result.get(report.getTcc().getPeriodoInicial()).get(report.getReporterRole()).add(report);
		}

		return result;
	}

	@Override
	public CoordenadorRepository getRepository() {
		return coordenadorRepository;
	}

	@Override
	public String getEntityName() {
		return "Coordenador";
	}

}
