package com.K955.Notes.API.Service.ImpL;

import com.K955.Notes.API.DTOs.Note.*;
import com.K955.Notes.API.Entity.Note;
import com.K955.Notes.API.Entity.User;
import com.K955.Notes.API.Exceptions.BadRequestException;
import com.K955.Notes.API.Exceptions.ResourceNotFoundException;
import com.K955.Notes.API.Mapper.NoteMapper;
import com.K955.Notes.API.Repository.NoteRepository;
import com.K955.Notes.API.Repository.UserRepository;
import com.K955.Notes.API.Service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public NoteUpdateResponse pinNote(Long userId, Long id, PinNoteRequest request) {
        User user = getUser(userId);
        Note note = getNote(id);
        StringBuilder sb = new StringBuilder();

        if(!note.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Not Accessible");
        }

        if(note.getPinned().equals(false) && request.pin().equals(true)) { //pin
            note.setPinned(request.pin());
            noteRepository.save(note);
            sb.append("Note Pinned Successfully");
        }
        else if(note.getPinned().equals(true) && request.pin().equals(true)) { //already pinned
            sb.append("Note is already Pinned");
        }
        else if(note.getPinned().equals(true) && request.pin().equals(false)) { //unpin
            note.setPinned(request.pin());
            noteRepository.save(note);
            sb.append("Note Unpinned Successfully");
        }
        else {
            sb.append("Note is not Pinned");
        }

        return new NoteUpdateResponse(id, note.getTitle(), sb.toString());
    }

    @Override
    public NoteUpdateResponse archiveNote(Long userId, Long id, ArchiveNoteRequest request) {
        User user = getUser(userId);
        Note note = getNote(id);
        StringBuilder sb = new StringBuilder();

        if(!note.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Not Accessible");
        }

        if(note.getArchived().equals(false) && request.archive().equals(true)) { //archive
            note.setArchived(request.archive());
            noteRepository.save(note);
            sb.append("Note Archived Successfully");
        }
        else if(note.getArchived().equals(true) && request.archive().equals(true)) { //already archived
            sb.append("Note is already Archived");
        }
        else if(note.getArchived().equals(true) && request.archive().equals(false)) { //restore
            note.setArchived(request.archive());
            noteRepository.save(note);
            sb.append("Note Restored Successfully");
        }
        else { //already restored
            sb.append("Note is not Archived");
        }

        return new NoteUpdateResponse(id, note.getTitle(), sb.toString());
    }

    @Override
    public List<NoteResponse> getNotesSearch(Long userId, String keyword) {
        List<Note> noteList = noteRepository.findByUserIdAndTitleContainingIgnoreCase(userId, keyword);
        return noteList.stream()
                .map(noteMapper::toNoteResponse)
                .toList();
    }

    @Override
    public List<NoteResponse> getNotesSort(Long userId, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        List<Note> noteList = noteRepository.findByUserId(userId, sort);

        return noteList.stream()
                .map(noteMapper::toNoteResponse)
                .toList();
    }

    @Override
    public Page<NoteResponse> getNotesPage(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Note> notes = noteRepository.findByUserId(userId, pageable);

        return notes.map(noteMapper::toNoteResponse);
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
