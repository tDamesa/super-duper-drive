package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.model.IModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    FileMapper fileMapper;
    public List<File> getFiles() {
        return fileMapper.getFiles(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    public int createFile(String username, String filename, String contentType, String fileSize, byte[] file){

        // Path uploadPath = Paths.get("./uploads");
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        try (InputStream inputStream = file.getInputStream()) {
//            Path filePath = uploadPath.resolve(filename);
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        }
//        catch (IOException e ) {
//            throw new IOException("Could not save image file: " + filename, e);
//        }
        return fileMapper.insert(filename, contentType, fileSize, file, username);
    }

    public byte[] downloadFile(String filename) {
        File file = fileMapper.getFile(filename);
        return file.getFileData();
    }

    public void deleteFile(Integer fileId) {
       fileMapper.delete(fileId);
    }
}
