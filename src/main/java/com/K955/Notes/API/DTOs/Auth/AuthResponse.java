package com.K955.Notes.API.DTOs.Auth;

import com.K955.Notes.API.DTOs.User.UserProfileResponse;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {
}
