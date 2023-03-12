package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    FileService fileService;

    @Autowired
    NoteService noteService;

    @Autowired
    CredentialService credentialService;

    @Autowired
    EncryptionService encryptionService;

    @GetMapping
    public String getData(Model model) {
        getNote(model);
        getFiles(model);
        getCredentials(model);
        return "home";
    }

    public void getNote(Model model) {
        List<Note> notes = noteService.getNotes();
        model.addAttribute("notes", notes);
        System.out.println("notes");
    }

    public void getFiles(Model model){
        List<File> files = fileService.getFiles();
        model.addAttribute("files", files);
        files.stream().forEach(file -> System.out.println("filename: " + file.getFilename()));
    }

    public void getCredentials(Model model){
        List<Credential> credentials = credentialService.getCredentials();
        model.addAttribute("credentials", credentials);
        model.addAttribute("encryptionService", encryptionService);
    }
}
