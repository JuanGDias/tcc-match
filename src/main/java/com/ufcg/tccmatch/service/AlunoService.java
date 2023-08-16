package com.ufcg.tccmatch.service;

import java.util.List;

import com.ufcg.tccmatch.dto.AlunoDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.Aluno;

import com.ufcg.tccmatch.model.AreaEstudo;
import com.ufcg.tccmatch.model.Report;

import com.ufcg.tccmatch.model.Professor;


public interface AlunoService extends AbstractService<Aluno> {

	Report reportarProblemaOrientacao(long idTcc, String report) throws AppException;
	
	Aluno criaAluno(AlunoDTO alunoDTO) throws AppException;

	Aluno atualizaAluno(long idAluno, AlunoDTO alunoDTO) throws AppException;

	Aluno adicionarAreaEstudo(String areaEstudo) throws AppException;
	
	List<Professor> professoresMesmoInteresse() throws AppException;

}
