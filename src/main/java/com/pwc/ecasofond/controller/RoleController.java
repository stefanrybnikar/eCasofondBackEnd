package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.Role;
import com.pwc.ecasofond.request.body.add.AddRoleBody;
import com.pwc.ecasofond.request.body.update.UpdateRoleBody;
import com.pwc.ecasofond.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/role")
public class RoleController implements com.pwc.ecasofond.controller.Controller<Role, AddRoleBody, UpdateRoleBody> {
    @Autowired
    private RoleService roleService;

    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all roles")
    public ResponseEntity<Iterable<Role>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets a role")
    public ResponseEntity<Role> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of role")
            Long id
    ) {
        Role c = roleService.get(id);

        if (c == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds a role")
    public ResponseEntity<Role> add(@RequestBody AddRoleBody requestBody) {
        Role c = roleService.add(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @PostMapping(path = "/update")
    @Operation(summary = "Updates a role")
    public ResponseEntity<Role> update(@RequestBody UpdateRoleBody requestBody) {
        Role c = roleService.update(requestBody);

        if (c == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(c);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes a role")
    public ResponseEntity<Boolean> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of role")
            Long id
    ) {
        Boolean result = roleService.delete(id);

        if (!result)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().build();
    }
}
