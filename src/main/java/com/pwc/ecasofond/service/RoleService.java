package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Role;
import com.pwc.ecasofond.repository.RoleRepository;
import com.pwc.ecasofond.request.response.RoleResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private RoleResponse convertToResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        return roleResponse;
    }

    public Iterable<RoleResponse> getAll() {
        Iterable<Role> roles = roleRepository.findAll();
        ArrayList<RoleResponse> roleResponses = new ArrayList<>();

        for (Role role : roles) {
            roleResponses.add(convertToResponse(role));
        }

        return roleResponses;
    }

    public RoleResponse get(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null)
            return null;

        return convertToResponse(role);
    }
}
