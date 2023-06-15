package com.pwc.ecasofond.service;

import com.pwc.ecasofond.model.Role;
import com.pwc.ecasofond.repository.RoleRepository;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.RoleResponse;
import org.springframework.http.HttpStatus;
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

    public ApiResponse<Iterable<RoleResponse>> getAll() {
        ApiResponse<Iterable<RoleResponse>> response = new ApiResponse<>();
        Iterable<Role> roles = roleRepository.findAll();
        ArrayList<RoleResponse> roleResponses = new ArrayList<>();

        for (Role role : roles) {
            roleResponses.add(convertToResponse(role));
        }

        response.setData(roleResponses);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + roleResponses.size() + " roles");

        return response;
    }

    public ApiResponse<RoleResponse> get(Long id) {
        ApiResponse<RoleResponse> response = new ApiResponse<>();

        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Role not found");
            return response;
        }

        response.setData(convertToResponse(role));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Role found");

        return response;
    }
}
