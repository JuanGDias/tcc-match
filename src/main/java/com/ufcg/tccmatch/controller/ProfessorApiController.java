package com.ufcg.tccmatch.controller;

import java.util.List;

import com.ufcg.tccmatch.model.Report;
import com.ufcg.tccmatch.model.TccReal;
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

import com.ufcg.tccmatch.dto.ProfessorDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.Professor;
import com.ufcg.tccmatch.service.ProfessorService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProfessorApiController {
	
	@Autowired
	ProfessorService professorService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/professor/criaProfessor", method = RequestMethod.POST)
	public ResponseEntity<?> criarProfessor(@RequestBody ProfessorDTO professorDTO) {
		try {
			return new ResponseEntity<Professor>(professorService.criaProfessor(professorDTO), HttpStatus.CREATED);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/professor/atualizaProfessor/{idProfessor}", method = RequestMethod.POST)
	public ResponseEntity<?> atualizarProfessor(@PathVariable("idProfessor") long idProfessor, @RequestBody ProfessorDTO professorDTO){
		try {
			return new ResponseEntity<Professor>(professorService.atualizaProfessor(idProfessor, professorDTO), HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/professor/removeProfessor/{idProfessor}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerProfessor(@PathVariable("idProfessor") long idProfessor){
		try {
			professorService.remover(idProfessor);
			return new ResponseEntity<String>("Professor removido!", HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}
	
	@RequestMapping(value = "/professores", method = RequestMethod.GET)
	public ResponseEntity<?> listarProfessores() {
		try {
			return new ResponseEntity<List<Professor>>(professorService.getAll(), HttpStatus.OK);
		} catch (AppException exception) {
			return new ResponseEntity<AppException>(exception, exception.getHttpStatus());
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/professor/atualizarQuotaProfessor}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarQuotaProfessor(@RequestBody int quota){
		try {
			return new ResponseEntity<Professor>(professorService.atualizarQuotaProfessor(quota), HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/professor/selecionarAreasDeEstudoDeInteresse", method = RequestMethod.PUT)
	public ResponseEntity<?> selecionarAreasDeEstudoDeInteresse(@RequestBody List<String> listNomesAreasDeEstudo){
		try {
			return new ResponseEntity<Professor>(professorService.selecionarAreasDeEstudoDeInteresse(listNomesAreasDeEstudo), HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/professor/reportarProblemaOrientacao/{idTcc}", method = RequestMethod.PUT)
	public ResponseEntity<?> reportarProblemaOrientacao(@PathVariable("idTcc") long  idTcc, @RequestBody String report){
		try {
			return new ResponseEntity<Report>(professorService.reportarProblemaOrientacao(idTcc, report), HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}

	@PreAuthorize("hasRole('TEACHER')") //US31
	@RequestMapping(value = "/listar-orientacoes-em-curso", method = RequestMethod.GET)
	public ResponseEntity<?> listarOrientacoesEmCurso() throws Exception {
		try {
			return new ResponseEntity<List<TccReal>>(professorService.listarOrientacoesEmCurso(), HttpStatus.OK);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}


}
