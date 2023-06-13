package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Boolean existsByName(String name);

    Boolean existsByLevel(Integer level);
}
