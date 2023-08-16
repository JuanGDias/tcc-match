package com.ufcg.tccmatch.controller;

import com.ufcg.tccmatch.dto.AlunoDTO;
import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.Professor;
import com.ufcg.tccmatch.model.Report;
import com.ufcg.tccmatch.service.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class CoordenadorApiController {
	
	@Autowired
	CoordenadorService coordenadorService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/coordenador/relatorio", method = RequestMethod.GET)
	public ResponseEntity<?> gerarRelatorioProblemas() {
		return new ResponseEntity<Map<String, Map<String, List<Report>>>>(coordenadorService.gerarRelatorioProblemas(), HttpStatus.OK);
	}

}
