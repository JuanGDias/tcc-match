package com.ufcg.tccmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.ufcg.tccmatch.model.*;
import com.ufcg.tccmatch.repository.AreaEstudoRepository;
import com.ufcg.tccmatch.repository.ReportRepository;
import com.ufcg.tccmatch.repository.TccRealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufcg.tccmatch.dto.ProfessorDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.repository.ProfessorRepository;

@Service
@Transactional
public class ProfessorServiceImpl extends AbstractServiceImpl<Professor, ProfessorRepository> implements ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	AreaEstudoRepository areaEstudoRepository;

	@Autowired
	TccRealRepository tccRealRepository;

	@Autowired
	ReportRepository reportRepository;
	
	@Override
	public Professor criaProfessor(ProfessorDTO professorDTO) throws AppException {
		Optional<Professor> optionalProfessor = professorRepository.findByNome(professorDTO.getNome());

		if(optionalProfessor.isPresent()) {
			throw new AppException("Professor já foi cadastrado", HttpStatus.CONFLICT);
		}

		Usuario usuProfessor = new Usuario(professorDTO.getUsername(), encoder.encode(professorDTO.getPassword()), Set.of(getRoleByName("TEACHER")));
		Professor novoProfessor = new Professor(professorDTO.getNome(), professorDTO.getEmail(), professorDTO.getLaboratorio(), usuProfessor);

		usuarioRepository.save(usuProfessor);
		professorRepository.save(novoProfessor);
		return novoProfessor;
	}
	
	@Override
	public Professor atualizaProfessor(long idProfessor, ProfessorDTO professorDTO) throws AppException {
		Professor professorAtualizado = this.getById(idProfessor);
		
		professorAtualizado.setNome(professorDTO.getNome());
		professorAtualizado.setEmail(professorDTO.getEmail());
		professorAtualizado.setLaboratorios(professorDTO.getLaboratorio());
				
		professorRepository.save(professorAtualizado);
		return professorAtualizado;
	}

	@Override
	public Professor atualizarQuotaProfessor(int quota) throws AppException {
		Professor professor = getProfessorByUser();
		professor.setQuotaDeDisponibilidade(quota);
		professorRepository.save(professor);
		return professor;
	}

	@Override
	public Professor selecionarAreasDeEstudoDeInteresse(List<String> listNomesAreasDeEstudo) throws AppException {
		Professor professor = getProfessorByUser();

		List<AreaEstudo> listAreaEstudos = new ArrayList<>();
		for (String nomeAreaDeEstudo : listNomesAreasDeEstudo) {
			Optional<AreaEstudo> optionalAreaDeEstudo = areaEstudoRepository.findByNome(nomeAreaDeEstudo);
			if (optionalAreaDeEstudo.isPresent()){
				listAreaEstudos.add(optionalAreaDeEstudo.get());
			}else {
				throw new AppException("Area de estudo não cadastrada", HttpStatus.NOT_FOUND);

			}
		}
		professor.setAreasDeEstudo(listAreaEstudos);
		professorRepository.save(professor);
		return professor;
	}

	@Override
	public Report reportarProblemaOrientacao(long idTcc, String report) throws AppException {
		Professor professor = getProfessorByUser();

		Optional<TccReal> tccOptional = tccRealRepository.findById(idTcc);

		if(tccOptional.isEmpty()){
			throw new AppException(String.format("Tcc com ID %d não encontrado", idTcc), HttpStatus.NOT_FOUND);
		}

		Report reporting= new Report(professor.getId(), professor.getNome(), report, "TEACHER", tccOptional.get());
		reportRepository.save(reporting);
		return reporting;
	}

	private Professor getProfessorByUser() throws AppException {
		Optional<Professor> professorOptional = professorRepository.findByUsuario(getCurrentUser());

		if(professorOptional.isEmpty()){
			throw new AppException("Usuario logado não é um professor", HttpStatus.NOT_FOUND);
		}

		return professorOptional.get();
	}


	@Override
	public List<TccReal> listarOrientacoesEmCurso() throws AppException {
		return tccRealRepository.findAllByOrientadorAndStatus(getProfessorByUser(), StatusOrientacao.EM_ANDAMENTO);
	}

	@Override
	public ProfessorRepository getRepository() {
		return professorRepository;
	}

	@Override
	public String getEntityName() {
		return "Professor";
	}
	
}
