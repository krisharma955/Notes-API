package com.K955.Notes.API.Mapper;

import com.K955.Notes.API.DTOs.Note.NoteResponse;
import com.K955.Notes.API.Entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(source = "user.id", target = "user.id")
    @Mapping(source = "user.name", target = "user.name")
    @Mapping(source = "user.username", target = "user.username")
    NoteResponse toNoteResponse(Note note);

    List<NoteResponse> toListOfNoteResponse(List<Note> noteList);

}
