package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.body.add.AddUserBody;
import com.pwc.ecasofond.request.body.update.ResetUserPasswordBody;
import com.pwc.ecasofond.request.body.update.UpdateUserBody;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.UserResponse;
import com.pwc.ecasofond.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/v1/user")
@Tag(name = "User")
public class UserController implements com.pwc.ecasofond.controller.Controller<UserResponse, AddUserBody, UpdateUserBody> {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Override
    @GetMapping(path = "/all")
    @Operation(summary = "Gets all users")
    public ResponseEntity<ApiResponse<Iterable<UserResponse>>> getAll() {
        ApiResponse<Iterable<UserResponse>> result = userService.getAll();

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @GetMapping(path = "/{id}")
    @Operation(summary = "Gets an User")
    public ResponseEntity<ApiResponse<UserResponse>> get(
            @PathVariable(name = "id")
            @Parameter(description = "Id of user")
            Long id
    ) {
        ApiResponse<UserResponse> u = userService.get(id);

        return ResponseEntity.status(u.getStatus()).body(u);
    }

    @Override
    @PostMapping(path = "/add")
    @Operation(summary = "Adds an user")
    public ResponseEntity<ApiResponse<UserResponse>> add(@RequestBody AddUserBody requestBody) {
        ApiResponse<UserResponse> u = userService.add(requestBody);

        return ResponseEntity.status(u.getStatus()).body(u);
    }

    @Override
    @PutMapping(path = "/update")
    @Operation(summary = "Updates an user")
    public ResponseEntity<ApiResponse<UserResponse>> update(@RequestBody UpdateUserBody requestBody) {
        ApiResponse<UserResponse> u = userService.update(requestBody);

        return ResponseEntity.status(u.getStatus()).body(u);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    @Operation(summary = "Deletes an User")
    public ResponseEntity<ApiResponse<Boolean>> delete(
            @PathVariable(name = "id")
            @Parameter(description = "Id of user")
            Long id
    ) {
        ApiResponse<Boolean> result = userService.delete(id);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PostMapping(path = "/reset-password")
    @Operation(summary = "Resets password of an user")
    public ResponseEntity<ApiResponse<Boolean>> resetPassword(
            @RequestBody ResetUserPasswordBody requestBody
    ) {
        ApiResponse<Boolean> result = userService.resetPassword(requestBody);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
