package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    @Autowired
    CredentialMapper credentialMapper;

    @Autowired
    EncryptionService encryptionService;
    public List<Credential> getCredentials() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return credentialMapper.getCredentials(auth.getName());
    }

    public int createCredential(Credential credential, String currentUsername){
        Credential newCredential = encryptPasswordAndCreateCredential(credential);
        return credentialMapper.insert(newCredential.getUrl(), newCredential.getUsername(), newCredential.getPassword(), newCredential.getKey(), currentUsername);
    }

    public void updateCredential(Credential credential) {
        Credential newCredential = encryptPasswordAndCreateCredential(credential);
        credentialMapper.update(newCredential);
    }

    private Credential encryptPasswordAndCreateCredential(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        return new Credential(credential.getCredentialId(), credential.getUrl(), credential.getUsername(), encryptedPassword, encodedKey);
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }
}

