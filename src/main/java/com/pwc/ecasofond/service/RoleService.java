package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Role;
import com.pwc.ecasofond.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Iterable<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role get(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
