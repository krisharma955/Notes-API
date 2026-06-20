package com.K955.Notes.API.Service;

import com.K955.Notes.API.DTOs.Auth.AuthResponse;
import com.K955.Notes.API.DTOs.Auth.LoginRequest;
import com.K955.Notes.API.DTOs.Auth.SignupRequest;
import jakarta.validation.Valid;

public interface AuthService {
    AuthResponse signup(@Valid SignupRequest request);

    AuthResponse login(@Valid LoginRequest request);
}
