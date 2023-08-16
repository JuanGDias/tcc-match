package com.ufcg.tccmatch.service;

import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.AreaEstudo;

public interface AreaEstudoService extends AbstractService<AreaEstudo>{

	AreaEstudo cadastraAreaEstudo(String areaDeEstudo) throws AppException;

	AreaEstudo getAreaEstudo(String areaEstudoNome) throws AppException;
	
}
