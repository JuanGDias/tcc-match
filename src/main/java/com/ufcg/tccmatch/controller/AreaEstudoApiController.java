package com.ufcg.tccmatch.controller;

import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.AreaEstudo;
import com.ufcg.tccmatch.service.AreaEstudoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AreaEstudoApiController {

	@Autowired
	AreaEstudoService areaEstudoService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/areaEstudo/cadastraAreaEstudo/", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarAreaEstudo(@RequestBody String areaDeEstudo) {
		try {
			return new ResponseEntity<AreaEstudo>(areaEstudoService.cadastraAreaEstudo(areaDeEstudo), HttpStatus.CREATED);
		}catch(AppException exception) {
			return new ResponseEntity<String>(exception.getMessage(), exception.getHttpStatus());
		}
	}
}