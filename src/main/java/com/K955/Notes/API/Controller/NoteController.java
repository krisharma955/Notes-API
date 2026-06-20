package com.K955.Notes.API.Controller;

import com.K955.Notes.API.DTOs.Note.NoteRequest;
import com.K955.Notes.API.DTOs.Note.NoteResponse;
import com.K955.Notes.API.DTOs.Note.UpdateNoteRequest;
import com.K955.Notes.API.Entity.Note;
import com.K955.Notes.API.Security.JwtAuthUtil;
import com.K955.Notes.API.Service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;
    private final JwtAuthUtil jwtAuthUtil;

    @PostMapping("/create")
    public ResponseEntity<NoteResponse> createNewNote(@Valid @RequestBody NoteRequest request) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNewNote(userId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@Valid @PathVariable Long id) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(noteService.getNoteById(userId, id));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllUserNotes() {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(noteService.getAllUserNotes(userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNoteById(@Valid @PathVariable Long id, @Valid @RequestBody UpdateNoteRequest request) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(noteService.updateNoteById(userId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@Valid @PathVariable Long id) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        noteService.deleteNoteById(userId, id);
        return ResponseEntity.noContent().build();
    }

}
