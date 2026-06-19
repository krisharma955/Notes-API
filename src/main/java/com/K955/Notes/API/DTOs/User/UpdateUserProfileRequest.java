package com.K955.Notes.API.DTOs.User;

public record UpdateUserProfileRequest(
        String name,
        String password
) {
}
