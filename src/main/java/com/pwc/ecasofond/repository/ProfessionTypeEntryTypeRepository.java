package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.ProfessionTypeEntryType;
import org.springframework.data.repository.CrudRepository;

public interface ProfessionTypeEntryTypeRepository extends CrudRepository<ProfessionTypeEntryType, Long> {
    Boolean existsByProfessionIdAndEntryTypeId(Long professionId, Long entryTypeId);
}
