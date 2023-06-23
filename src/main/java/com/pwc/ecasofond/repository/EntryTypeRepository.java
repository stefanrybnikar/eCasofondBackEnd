package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.EntryType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryTypeRepository extends CrudRepository<EntryType, Long> {
    Boolean existsByName(String name);

    Iterable<EntryType> findAllByCompanyId(Long companyId);
}
