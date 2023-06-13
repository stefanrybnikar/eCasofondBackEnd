package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {

}
