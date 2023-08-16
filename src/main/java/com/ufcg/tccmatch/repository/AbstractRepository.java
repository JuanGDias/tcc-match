package com.ufcg.tccmatch.repository;

import com.ufcg.tccmatch.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {
}
