package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.Profession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long> {
    Boolean existsByName(String name);
}
