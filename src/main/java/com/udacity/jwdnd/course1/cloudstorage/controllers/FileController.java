package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    FileService fileService;
    @GetMapping
    public String getFiles(Model model){
        List<File> files = fileService.getFiles();
        System.out.println("called: getFiles" );
        model.addAttribute("files", files);
        return "home";
    }
    @PostMapping
    public String createFile(Model model, @RequestParam("fileUpload") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            model.addAttribute("result", "error");
            model.addAttribute("message", "Failed to store empty file.");
            return "result";
        }
        try {
            List<File> files = fileService.getFiles();

            for (File file : files) {
                if (file.getFilename().equals(multipartFile.getOriginalFilename())){
                    model.addAttribute("result", "error");
                    model.addAttribute("message", "File name already exist.");
                    return "result";
                }
            }

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String contentType = multipartFile.getContentType();
            String fileSize = String.valueOf(multipartFile.getSize());
            InputStream inputStream = multipartFile.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();

            byte[] file =  out.toByteArray();

            fileService.createFile(username, filename, contentType, fileSize, file);
            model.addAttribute("result", "success");
        } catch (IOException e) {
            model.addAttribute("result", "error");
            model.addAttribute("message", "Could not upload file.");
            e.printStackTrace();
        }

        return "result";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("filename") String filename, Model model) throws IOException {
        byte[] file = fileService.downloadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename+ "\"")
                .body(file);
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam Integer fileId, Model model){
        fileService.deleteFile(fileId);
        model.addAttribute("result", "success");
        return "result";
    }
}
