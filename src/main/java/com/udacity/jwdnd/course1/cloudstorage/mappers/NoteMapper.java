package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userid = (SELECT userid FROM USERS WHERE username = #{username})")
    List<Note> getNotes(String username);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userid) VALUES (#{noteTitle}, #{noteDescription}, (SELECT userid FROM USERS WHERE username = #{username}))")
    int insert(String noteTitle, String noteDescription, String username);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void delete(Integer noteId);

    @Update("UPDATE NOTES SET noteTitle = '${noteTitle}', noteDescription = '${noteDescription}' WHERE noteId = ${noteId}")
    void update(Note note);
}
