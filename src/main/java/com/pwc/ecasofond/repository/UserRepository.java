package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
