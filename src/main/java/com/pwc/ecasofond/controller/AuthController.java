package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.body.auth.LoginBody;
import com.pwc.ecasofond.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping(path = "/auth")
@Tag(name = "Auth")
public class AuthController {
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    @Operation(summary = "When authenticated with username and password, returns a token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody LoginBody requestBody) {
        return true;
    }

    @PostMapping("/logout")
    public Boolean logout() {
        return true;
    }
}
