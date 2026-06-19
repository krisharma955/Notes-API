package com.K955.Notes.API.DTOs.Note;

public record UpdateNoteRequest(
        String title,
        String content
) {
}
