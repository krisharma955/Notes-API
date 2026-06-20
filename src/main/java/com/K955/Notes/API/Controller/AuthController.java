package com.K955.Notes.API.Controller;

import com.K955.Notes.API.DTOs.Auth.AuthResponse;
import com.K955.Notes.API.DTOs.Auth.LoginRequest;
import com.K955.Notes.API.DTOs.Auth.SignupRequest;
import com.K955.Notes.API.Security.JwtAuthFilter;
import com.K955.Notes.API.Security.JwtAuthUtil;
import com.K955.Notes.API.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtAuthFilter jwtAuthFilter;
    private final JwtAuthUtil jwtAuthUtil;

    @PostMapping("/signup")
    @Operation(summary = "User SignUp")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(request));
    }

    @PostMapping("/login")
    @Operation(summary = "User Login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
