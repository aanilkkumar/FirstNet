package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.PatEdu;
import com.example.easynotes.repository.PatEduRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Anil on 10/08/18.
 */
@RestController
@RequestMapping("/api")
public class PatEduController {

    @Autowired
    PatEduRepository noteRepository;

    @GetMapping("/notes")
    public List<PatEdu> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public PatEdu createNote(@Valid @RequestBody PatEdu note) {
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public PatEdu getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public PatEdu updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody PatEdu noteDetails) {

    	PatEdu note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        PatEdu updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
    	PatEdu note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
