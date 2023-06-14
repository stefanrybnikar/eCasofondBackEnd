package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.Role;
import com.pwc.ecasofond.request.response.RoleResponse;
import com.pwc.ecasofond.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/v1/role")
@Tag(name = "Role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "/all")
    @Operation(summary = "Gets all roles")
    public ResponseEntity<Iterable<RoleResponse>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets a role")
    public ResponseEntity<RoleResponse> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of role")
            Long id
    ) {
        RoleResponse c = roleService.get(id);

        if (c == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(c);
    }
}
