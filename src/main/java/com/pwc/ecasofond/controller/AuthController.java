package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.repository.UserRepository;
import com.pwc.ecasofond.request.body.auth.LoginBody;
import com.pwc.ecasofond.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping(path = "/v1/auth")
@Tag(name = "Auth")
public class AuthController {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthController(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    @Operation(summary = "When authenticated with username and password, returns a token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    @PostMapping("/login")
    @Operation(summary = "When authenticated with username and password, returns true")
    public Boolean login(@RequestBody LoginBody requestBody) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String username = requestBody.getUsername();
        String password = requestBody.getPassword();

        String passwordHash = encoder.encode(password);

        return userRepository.existsByUsernameAndPassword(username, passwordHash);
    }

    @PostMapping("/logout")
    @Operation(summary = "When authenticated with username and password, returns true")
    public Boolean logout() {
        return false;
    }
}
