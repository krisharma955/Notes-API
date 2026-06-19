package com.K955.Notes.API.Repository;

import com.K955.Notes.API.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("""
            SELECT n FROM Note n
            WHERE n.deletedAt IS NULL
            AND n.user.id = :userId
            ORDER BY n.updatedAt DESC
            """)
    List<Note> getAllNotes(@Param("userId") Long userId);

}
