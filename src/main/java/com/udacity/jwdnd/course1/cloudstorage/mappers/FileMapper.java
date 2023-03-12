package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid = (SELECT userid FROM USERS WHERE username = #{username})")
    List<File> getFiles(String username);

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFile(String filename);
    @Insert("INSERT INTO FILES (filename, contentType, fileSize, fileData, userId) VALUES (#{filename}, #{contentType}, #{fileSize}, #{fileData}, (SELECT userid FROM USERS WHERE username = #{username}))")
    int insert(String filename, String contentType, String fileSize, byte[] fileData, String username);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void delete(Integer fileId);
}
