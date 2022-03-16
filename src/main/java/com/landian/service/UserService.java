package com.landian.service;

import com.landian.domain.User;

import java.util.List;

public interface UserService {

    void updateUserById(String username,String password,String email,int id);

    User findUserById(int id);

    public List<User> findAllUser(int currentPage);

    public User login(String username,String password);

    public void register(String username, String password, String email);

    public User verifyUsername(String username);

    public void deleteUser(int id);
}
