package com.K955.Notes.API.Service;

import com.K955.Notes.API.DTOs.Note.*;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoteService {
    NoteResponse createNewNote(Long userId, NoteRequest request);

    NoteResponse getNoteById(Long userId, Long id);

    NoteResponse updateNoteById(Long userId, Long id, UpdateNoteRequest request);

    void deleteNoteById(Long userId, Long id);

    NoteUpdateResponse pinNote(Long userId, Long id, @Valid PinNoteRequest request);

    NoteUpdateResponse archiveNote(Long userId, Long id, @Valid ArchiveNoteRequest request);

    Page<NoteResponse> getAllUserNotes(Long userId, String keyword, int page, int size, String sortBy, String direction);
}
