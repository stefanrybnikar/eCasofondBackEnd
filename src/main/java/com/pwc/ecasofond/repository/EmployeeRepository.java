package com.pwc.ecasofond.repository;

import com.pwc.ecasofond.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
