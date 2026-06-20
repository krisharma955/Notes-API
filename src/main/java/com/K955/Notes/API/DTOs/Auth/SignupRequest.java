package com.K955.Notes.API.DTOs.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignupRequest(

        @NotNull
        String name,

        @Email
        @NotNull
        String username,

        @NotNull
        String password
) {
}
