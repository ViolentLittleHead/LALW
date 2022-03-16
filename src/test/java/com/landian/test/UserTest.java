package com.landian.test;

import com.landian.domain.User;
import com.landian.service.UserService;
import com.landian.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void login(){
        User user = userService.login("zhangsan","123123");
        System.out.println(user);
    }

    @Test
    public void register(){
        userService.register("lisi","123123","2312424124@qq.com");
    }

    @Test
    public void checkUsername(){
        User user = userService.verifyUsername("hangsan");
        System.out.println(user);
    }

    @Test
    public void deleteUser(){
        userService.deleteUser(3);
    }
}
