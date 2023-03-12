package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid = (SELECT userid FROM USERS WHERE username = #{currentUsername})")
    List<Credential> getCredentials(String currentUsername);

    @Insert("INSERT INTO CREDENTIALS (url, username, password, key, userid) VALUES (#{url}, #{username}, #{password}, #{key}, (SELECT userid FROM USERS WHERE username = #{currentUsername}))")
    Integer insert(String url, String username, String password, String key, String currentUsername);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void delete(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = '${url}', username = '${username}', key = '${key}', password = '${password}' WHERE credentialId = ${credentialId}")
    void update(Credential credential);
}
