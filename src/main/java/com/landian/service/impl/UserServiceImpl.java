package com.landian.service.impl;

import com.github.pagehelper.PageHelper;
import com.landian.domain.User;
import com.landian.mapper.AlgorithmMapper;
import com.landian.mapper.AnswerMapper;
import com.landian.mapper.QuestionMapper;
import com.landian.mapper.UserMapper;
import com.landian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private AlgorithmMapper algorithmMapper;

    @Override
    public void updateUserById(String username, String password, String email, int id) {
        userMapper.updateUserById(username,password,email,id);
    }

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<User> findAllUser(int currentPage) {
        PageHelper.startPage(currentPage,8);
        return userMapper.findAllUser();
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }

    @Override
    public void register(String username, String password, String email) {
        userMapper.register(username,password,email);
    }

    @Override
    public User verifyUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public void deleteUser(int id) {
        questionMapper.deleteQuestionByUid(id);
        algorithmMapper.deleteAlgorithmByUid(id);
        answerMapper.deleteAnswerByUid(id);
        userMapper.deleteUserById(id);
    }
}
