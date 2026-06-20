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

    List<NoteResponse> getAllUserNotes(Long userId);

    NoteUpdateResponse pinNote(Long userId, Long id, @Valid PinNoteRequest request);

    NoteUpdateResponse archiveNote(Long userId, Long id, @Valid ArchiveNoteRequest request);

    List<NoteResponse> getNotesSearch(Long userId, String keyword);

    List<NoteResponse> getNotesSort(Long userId, String sortBy, String direction);

    Page<NoteResponse> getNotesPage(Long userId, int page, int size);
}
