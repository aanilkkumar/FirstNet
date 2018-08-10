package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.PatReg;
import com.example.easynotes.repository.PatRegRepository;
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
public class PatRegController {

    @Autowired
    PatRegRepository noteRepository;

    @GetMapping("/notes")
    public List<PatReg> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public PatReg createNote(@Valid @RequestBody PatReg note) {
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public PatReg getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public PatReg updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody PatReg noteDetails) {

    	PatReg note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        PatReg updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
    	PatReg note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
