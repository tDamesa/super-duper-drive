package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {
    private Integer fileId;
    private String filename;
    private String contentType;
    private String fileSize;
    private byte[] fileData;


    public File(Integer fileId, String filename, String contentType, String fileSize, byte[] fileData) {
        this.fileId = fileId;
        this.filename = filename;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
