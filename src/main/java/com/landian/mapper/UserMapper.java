package com.landian.mapper;

import com.landian.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    void updateUserById(@Param("username")String username, @Param("password")String password,@Param("email")String email,@Param("id")int id);

    User findUserById(int id);

    List<User> findAllUser();

    User login(@Param("username")String username, @Param("password")String password);

    void register(@Param("username")String username, @Param("password")String password, @Param("email")String email);

    User findUserByUsername(@Param("username")String username);

    void deleteUserById(@Param("id")int id);
}
