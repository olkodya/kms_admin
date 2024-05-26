package com.example.kms.controller;

import com.example.kms.entity.User;
import com.example.kms.form.*;
import com.example.kms.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8081", "https://kms2-production.up.railway.app"})
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @Operation(summary = "User authentication", description = "Returns user data after successful authentication")
    @PostMapping("/users/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthForm form) {
        return ResponseEntity.ok(service.auth(form));
    }

    @Operation(summary = "User creation", description = "Returns user data after successful creation")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody RegForm form) {
        return new ResponseEntity<>(service.createUser(form), HttpStatus.CREATED);
    }

    @Operation(summary = "Token refresh", description = "Returns refreshed token")
    @GetMapping("/users/refresh-token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(HttpServletRequest request) {
        TokenRefreshResponse token = service.refreshToken(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(token);
    }

}