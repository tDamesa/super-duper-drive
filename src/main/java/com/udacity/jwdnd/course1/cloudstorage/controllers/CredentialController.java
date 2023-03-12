package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    @Autowired
    CredentialService credentialService;
    @Autowired
    EncryptionService encryptionService;
    @GetMapping
    public String getCredentials(Model model){
        List<Credential> credentials = credentialService.getCredentials();
        model.addAttribute("credentials", credentials);
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

    @PostMapping
    public String createCredential(@ModelAttribute("cred") Credential credential, Model model, Authentication authentication) {
        if (credential.getCredentialId() == null)
            credentialService.createCredential(credential, authentication.getName());
        else
            credentialService.updateCredential(credential);
        model.addAttribute("result", "success");
        return "result";
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam Integer credentialId, Model model){
        credentialService.deleteCredential(credentialId);
        model.addAttribute("result", "success");
        return "result";
    }
}
