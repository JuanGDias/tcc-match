package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.dto.ProfessorDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.Professor;
import com.ufcg.tccmatch.model.Report;
import com.ufcg.tccmatch.model.TccReal;

import java.util.List;

public interface ProfessorService extends AbstractService<Professor>{

	Professor criaProfessor(ProfessorDTO professorDTO) throws AppException;
	
	Professor atualizaProfessor(long idProfessor, ProfessorDTO professorDTO) throws AppException;

	Professor atualizarQuotaProfessor(int quota) throws AppException;

	Professor selecionarAreasDeEstudoDeInteresse(List<String> listAreasDeEstudo) throws AppException;

	Report reportarProblemaOrientacao(long idTcc, String report) throws AppException;

	List<TccReal> listarOrientacoesEmCurso() throws AppException;

}
