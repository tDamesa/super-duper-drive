package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
     User getUser(String username);

    @Insert("INSERT INTO USERS (username, firstName, lastName, password, salt) VALUES (#{username}, #{firstName}, #{lastName}, #{password}, #{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Delete("DELETE FROM USERS WHERE userId = #{userId}")
    void delete(Integer userid);

}
