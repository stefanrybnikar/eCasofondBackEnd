package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.ProfessionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionTypeRepository extends CrudRepository<ProfessionType, Long> {
    Boolean existsByName(String name);
}
