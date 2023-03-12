package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NoteService noteService;
    @GetMapping
    public String getNotes(Model model){
        List<Note> notes = noteService.getNotes();
        model.addAttribute("notes", notes);
        return "home";
    }

    @PostMapping
    public String createNote(Note note, Model model){
        if (note.getNoteId() == 0)
            noteService.createNote(note);
        else
            noteService.updateNote(note);
        model.addAttribute("result", "success");
        return "result";
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam Integer noteId, Model model){
        noteService.deleteNote(noteId);
        model.addAttribute("result", "success");
        return "result";
    }

}
