package com.K955.Notes.API.Service.ImpL;

import com.K955.Notes.API.DTOs.Note.NoteRequest;
import com.K955.Notes.API.DTOs.Note.NoteResponse;
import com.K955.Notes.API.DTOs.Note.UpdateNoteRequest;
import com.K955.Notes.API.Entity.Note;
import com.K955.Notes.API.Entity.User;
import com.K955.Notes.API.Exceptions.BadRequestException;
import com.K955.Notes.API.Exceptions.ResourceNotFoundException;
import com.K955.Notes.API.Mapper.NoteMapper;
import com.K955.Notes.API.Repository.NoteRepository;
import com.K955.Notes.API.Repository.UserRepository;
import com.K955.Notes.API.Service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpL implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NoteMapper noteMapper;

    @Override
    public NoteResponse createNewNote(Long userId, NoteRequest request) {
        User user = getUser(userId);

        Note note = Note.builder()
                .user(user)
                .title(request.title())
                .content(request.content())
                .build();

        Note saved = noteRepository.save(note);
        return noteMapper.toNoteResponse(saved);
    }

    @Override
    public NoteResponse getNoteById(Long userId, Long id) {
        User user = getUser(userId);
        Note note = getNote(id);
        if(!note.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Not Accessible");
        }
        return noteMapper.toNoteResponse(note);
    }

    @Override
    public NoteResponse updateNoteById(Long userId, Long id, UpdateNoteRequest request) {
        User user = getUser(userId);
        Note oldNote = getNote(id);

        if(!oldNote.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Not Accessible");
        }

        if(request.title() != null) oldNote.setTitle(request.title());
        if(request.content() != null) oldNote.setContent(request.content());

        Note saved = noteRepository.save(oldNote);
        return noteMapper.toNoteResponse(saved);
    }

    @Override
    public void deleteNoteById(Long userId, Long id) {
        User user = getUser(userId);
        Note note = getNote(id);

        if(!note.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Not Accessible");
        }

        note.setDeletedAt(Instant.now());
        noteRepository.save(note);
    }

    @Override
    public List<NoteResponse> getAllUserNotes(Long userId) {
        List<Note> noteList = noteRepository.getAllNotes(userId);
        return noteMapper.toListOfNoteResponse(noteList);
    }

    /// Util Methods

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId.toString(), "User"));
    }

    public Note getNote(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString(), "Note"));
    }

}
