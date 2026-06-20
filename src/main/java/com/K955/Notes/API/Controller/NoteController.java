package com.K955.Notes.API.Controller;

import com.K955.Notes.API.DTOs.Note.*;
import com.K955.Notes.API.Entity.Note;
import com.K955.Notes.API.Security.JwtAuthUtil;
import com.K955.Notes.API.Service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @PostMapping()
    @Operation(summary = "Create a new note")
    public ResponseEntity<NoteResponse> createNewNote(@Valid @RequestBody NoteRequest request) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNewNote(userId, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get note by Id")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(noteService.getNoteById(userId, id));
    }

    @GetMapping
    @Operation(summary = "Get all notes")
    public ResponseEntity<Page<NoteResponse>> getAllNotes(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(
                noteService.getAllUserNotes(userId, keyword, page, size, sortBy, direction)
        );
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update note by id")
    public ResponseEntity<NoteResponse> updateNoteById(@PathVariable Long id, @Valid @RequestBody UpdateNoteRequest request) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(noteService.updateNoteById(userId, id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete note by id")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        noteService.deleteNoteById(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/pin")
    @Operation(summary = "Pin & Unpin note by Id")
    public ResponseEntity<NoteUpdateResponse> pinNote(@PathVariable Long id, @Valid @RequestBody PinNoteRequest request) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(noteService.pinNote(userId, id, request));
    }

    @PatchMapping("/{id}/archive")
    @Operation(summary = "Archive & Restore note by Id")
    public ResponseEntity<NoteUpdateResponse> archiveNote(@PathVariable Long id, @Valid @RequestBody ArchiveNoteRequest request) {
        Long userId = jwtAuthUtil.getCurrentUserId();
        return ResponseEntity.ok(noteService.archiveNote(userId, id, request));
    }

}
