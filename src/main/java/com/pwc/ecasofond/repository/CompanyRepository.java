package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    Boolean existsByName(String companyName);
}
