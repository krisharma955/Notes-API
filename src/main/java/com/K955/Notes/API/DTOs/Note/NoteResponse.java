package com.K955.Notes.API.DTOs.Note;

import com.K955.Notes.API.DTOs.User.UserProfileResponse;

import java.time.Instant;

public record NoteResponse(
        Long id,
        String title,
        String content,
        Instant createdAt,
        UserProfileResponse user
) {
}
