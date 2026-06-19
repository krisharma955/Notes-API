package com.K955.Notes.API.DTOs.Note;

import jakarta.validation.constraints.NotNull;

public record NoteRequest(

        @NotNull
        String title,

        String content
) {
}
