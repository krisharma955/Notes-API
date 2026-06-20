package com.K955.Notes.API.DTOs.Note;

public record NoteUpdateResponse(
        Long id,
        String title,
        String message
) {
}
