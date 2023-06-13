package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
