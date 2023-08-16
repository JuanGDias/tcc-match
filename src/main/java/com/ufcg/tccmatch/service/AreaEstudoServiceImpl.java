package com.ufcg.tccmatch.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufcg.tccmatch.exception.AppException;
import com.ufcg.tccmatch.model.AreaEstudo;
import com.ufcg.tccmatch.repository.AreaEstudoRepository;

@Service
@Transactional
public class AreaEstudoServiceImpl extends AbstractServiceImpl<AreaEstudo, AreaEstudoRepository> implements AreaEstudoService{
	
	@Autowired
	AreaEstudoRepository areaEstudoRepository;
	
	@Override
	public AreaEstudo cadastraAreaEstudo(String areaDeEstudoNome) throws AppException {
		Optional<AreaEstudo> areaEstudoOptional = areaEstudoRepository.findByNome(areaDeEstudoNome);
		if(!areaEstudoOptional.isPresent()) {
			AreaEstudo areaEstudo = new AreaEstudo(areaDeEstudoNome);
			areaEstudoRepository.save(areaEstudo);
			return areaEstudo;
		}else {
			throw new AppException("Área de estudo já foi cadastrada", HttpStatus.CONFLICT);
		}
	}

	@Override
	public AreaEstudo getAreaEstudo(String areaDeEstudoNome) throws AppException {
		Optional<AreaEstudo> areaEstudoOptional = areaEstudoRepository.findByNome(areaDeEstudoNome);
		if(!areaEstudoOptional.isPresent()) {
			throw new AppException("Área de estudo não foi cadastrada", HttpStatus.NOT_FOUND);
		}
		
		AreaEstudo areaEstudo = areaEstudoOptional.get();
		return areaEstudo;
	}
	
	@Override
	public AreaEstudoRepository getRepository() {
		return areaEstudoRepository;
	}

	@Override
	public String getEntityName() {
		return "AreaEstudo";
	}	
}
