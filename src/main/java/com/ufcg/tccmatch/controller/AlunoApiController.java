package com.ufcg.tccmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.tccmatch.dto.AlunoDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.Aluno;
import com.ufcg.tccmatch.model.Professor;
import com.ufcg.tccmatch.model.Report;
import com.ufcg.tccmatch.service.AlunoService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AlunoApiController {
	
	@Autowired
	AlunoService alunoService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/aluno/criaAluno", method = RequestMethod.POST)
	public ResponseEntity<?> criarAluno(@RequestBody AlunoDTO alunoDTO) {
		try {
			return new ResponseEntity<Aluno>(alunoService.criaAluno(alunoDTO), HttpStatus.CREATED);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/aluno/atualizaAluno/{idAluno}", method = RequestMethod.POST)
	public ResponseEntity<?> atualizarAluno(@PathVariable("idAluno") long idAluno, @RequestBody AlunoDTO alunoDTO) {
		try {
			return new ResponseEntity<Aluno>(alunoService.atualizaAluno(idAluno, alunoDTO), HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/aluno/removeAluno/{idAluno}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerAluno(@PathVariable("idAluno") long idAluno) {
		try {
			alunoService.remover(idAluno);
			return new ResponseEntity<String>("Aluno removido!", HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}
	
	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/aluno/areasEstudo/", method = RequestMethod.POST)
	public ResponseEntity<?> adicionarAreaEstudo(@RequestBody String areaDeEstudo) {
		try {
			return new ResponseEntity<Aluno>(alunoService.adicionarAreaEstudo(areaDeEstudo), HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}
	
	@RequestMapping(value = "/alunos", method = RequestMethod.GET)
	public ResponseEntity<?> listarAlunos() {
		try {
			return new ResponseEntity<List<Aluno>>(alunoService.getAll(), HttpStatus.OK);
		} catch (AppException exception) {
			return new ResponseEntity<AppException>(exception, exception.getHttpStatus());
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/aluno/reportarProblemaOrientacao/{idTcc}", method = RequestMethod.PUT)
	public ResponseEntity<?> reportarProblemaOrientacao(@PathVariable("idTcc") long  idTcc,@RequestBody String report) {
		try {
			return new ResponseEntity<Report>(alunoService.reportarProblemaOrientacao(idTcc, report), HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/aluno/professoresInteressados", method = RequestMethod.GET)
	public ResponseEntity<?> professorAreaInteresseMutuo() {
		try {
			return new ResponseEntity<List<Professor>>(alunoService.professoresMesmoInteresse(), HttpStatus.OK);
		} catch (AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}
}

