package com.ufcg.tccmatch.repository;

import com.ufcg.tccmatch.model.AreaEstudo;
import java.util.Optional;

public interface AreaEstudoRepository extends AbstractRepository<AreaEstudo>{

	Optional<AreaEstudo> findByNome(String areaDeEstudo);


}
