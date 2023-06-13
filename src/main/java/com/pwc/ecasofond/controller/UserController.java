package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.model.User;
import com.pwc.ecasofond.request.body.add.AddUserBody;
import com.pwc.ecasofond.request.body.update.ResetUserPasswordBody;
import com.pwc.ecasofond.request.body.update.UpdateUserBody;
import com.pwc.ecasofond.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/user")
@Tag(name = "Role")
public class UserController implements com.pwc.ecasofond.controller.Controller<User, AddUserBody, UpdateUserBody> {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all users")
    public ResponseEntity<Iterable<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets an User")
    public ResponseEntity<User> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of user")
            Long id
    ) {
        User u = userService.get(id);

        if (u == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(u);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds an user")
    public ResponseEntity<User> add(@RequestBody AddUserBody requestBody) {
        User u = userService.add(requestBody);

        if (u == null)
            return ResponseEntity.internalServerError().build();
        else
            return ResponseEntity.ok(u);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates an user")
    public ResponseEntity<User> update(@RequestBody UpdateUserBody requestBody) {
        User u = userService.update(requestBody);

        if (u == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(u);
        }
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes an User")
    public ResponseEntity<Boolean> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of user")
            Long id
    ) {
        Boolean result = userService.delete(id);

        if (!result)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/reset-password")
    @Operation(summary = "Resets password of an user")
    public ResponseEntity<Boolean> resetPassword(
            @RequestBody ResetUserPasswordBody requestBody
    ) {
        Boolean result = userService.resetPassword(requestBody);

        if (!result)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().build();
    }
}
