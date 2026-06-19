package com.K955.Notes.API.Service;

import com.K955.Notes.API.DTOs.Note.NoteRequest;
import com.K955.Notes.API.DTOs.Note.NoteResponse;
import com.K955.Notes.API.DTOs.Note.UpdateNoteRequest;

import java.util.List;

public interface NoteService {
    NoteResponse createNewNote(Long userId, NoteRequest request);

    NoteResponse getNoteById(Long userId, Long id);

    NoteResponse updateNoteById(Long userId, Long id, UpdateNoteRequest request);

    void deleteNoteById(Long userId, Long id);

    List<NoteResponse> getAllUserNotes(Long userId);
}
