package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.dto.AlunoDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.*;
import com.ufcg.tccmatch.repository.*;

import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AlunoServiceImpl extends AbstractServiceImpl<Aluno, AlunoRepository> implements AlunoService {

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	AreaEstudoRepository areaEstudoRepository;

	@Autowired
	ReportRepository reportRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	TccRealRepository tccRealRepository;


	@Override
	public Aluno criaAluno(AlunoDTO alunoDTO) throws AppException {
		Optional<Aluno> optionalAluno = alunoRepository.findByMatricula(alunoDTO.getMatricula());

		if(optionalAluno.isPresent()) {
			throw new AppException("Aluno já foi cadastrado", HttpStatus.CONFLICT);
		}

		Usuario usuAluno = new Usuario(alunoDTO.getUsername(), encoder.encode(alunoDTO.getPassword()), Set.of(getRoleByName("STUDENT")));
		Aluno novoAluno = new Aluno(alunoDTO.getNome(), alunoDTO.getMatricula(), alunoDTO.getEmail(), alunoDTO.getPeriodoPrevistoParaConclusao(), usuAluno);

		usuarioRepository.save(usuAluno);
		alunoRepository.save(novoAluno);
		return novoAluno;
	}
	
	@Override
	public Aluno atualizaAluno(long idAluno, AlunoDTO alunoDTO) throws AppException {
		Aluno alunoAtualizado = this.getById(idAluno);
		
		alunoAtualizado.setNome(alunoDTO.getNome());
		alunoAtualizado.setMatricula(alunoDTO.getMatricula());
		alunoAtualizado.setEmail(alunoDTO.getEmail());
		alunoAtualizado.setPeriodoPrevistoParaConclusao(alunoDTO.getPeriodoPrevistoParaConclusao());
				
		alunoRepository.save(alunoAtualizado);
		return alunoAtualizado;
	}

	private Aluno getAlunoByMatricula(String matricula) throws AppException {
		Optional<Aluno> optionalAluno = alunoRepository.findByMatricula(matricula);
		if(optionalAluno.isEmpty()) {
			throw new AppException("Nenhum aluno foi encontrado com essa matrícula", HttpStatus.NOT_FOUND);
		}

		return optionalAluno.get();
	}
	
	@Override
	public Aluno adicionarAreaEstudo(String areaEstudoNome) throws AppException {
		Aluno aluno = getAlunoByUser();
		
		Optional<AreaEstudo> areaEstudoOptional = areaEstudoRepository.findByNome(areaEstudoNome);

		if(areaEstudoOptional.isEmpty()){
			throw new AppException("Área de estudo não foi cadastrada", HttpStatus.NOT_FOUND);
		}

		aluno.adicionarAreaEstudo(areaEstudoOptional.get());

		alunoRepository.save(aluno);
		return aluno;
	}

	@Override
	public Report reportarProblemaOrientacao(long idTcc, String report) throws AppException {
		Aluno aluno = getAlunoByUser();

		Optional<TccReal> tccOptional = tccRealRepository.findById(idTcc);

		if(tccOptional.isEmpty()){
			throw new AppException(String.format("Tcc com ID %d não encontrado", idTcc), HttpStatus.NOT_FOUND);
		}

		Report reporting = new Report(aluno.getId(), aluno.getNome(), report,"STUDENT", tccOptional.get());

		reportRepository.save(reporting);
		return reporting;
	}

	@Override
	public List<Professor> professoresMesmoInteresse() throws AppException {
		Aluno aluno = getAlunoByUser();

		Set<Professor> professoresInteressados = new HashSet<>();

		for (AreaEstudo areaEstudo: aluno.getAreasDeEstudo()) {
			professoresInteressados.addAll(professorRepository.findAllByAreasDeEstudo(areaEstudo));
		}

		professoresInteressados.removeIf(professor -> professor.getQuotaDeDisponibilidade() == 0);

		return new ArrayList<>(professoresInteressados);
	}

	private Aluno getAlunoByUser() throws AppException {
		Optional<Aluno> alunoOptional = alunoRepository.findByUsuario(getCurrentUser());

		if(alunoOptional.isEmpty()){
			throw new AppException("Usuario logado não é um aluno", HttpStatus.NOT_FOUND);
		}

		return alunoOptional.get();
	}

	@Override
	public AlunoRepository getRepository() {
		return alunoRepository;
	}

	@Override
	public String getEntityName() {
		return "Aluno";
	}


}


