package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.Entry;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Integer> {
}
