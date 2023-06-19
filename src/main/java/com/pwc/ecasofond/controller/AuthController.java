package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/auth")
@Tag(name = "Auth")
@SecurityRequirement(name = "basicAuth")
public class AuthController {
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    @Operation(summary = "When authenticated with username and password, returns a token")
    public ResponseEntity<ApiResponse<String>> token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);

        ApiResponse<String> response = new ApiResponse<>();
        response.setData(token);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Token generated");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
