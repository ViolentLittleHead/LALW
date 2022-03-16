package com.landian.controller;

import com.landian.domain.User;
import com.landian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/updateUser")
    public String updateUser(String username,String password,String email,int id,int currentPage){
        userService.updateUserById(username,password,email,id);
        return "redirect:/admin/userList?currentPage="+currentPage;
    }

    @RequestMapping("/findUserById")
    public ModelAndView findUserById(int id,int currentPage){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(id);
        modelAndView.addObject("user",user);
        modelAndView.addObject("currentPage",currentPage);
        modelAndView.setViewName("admin/admin_updateUser");

        return modelAndView;
    }

    @RequestMapping("/delete")
    public String deleteUserById(int id,int currentPage){
        userService.deleteUser(id);
        return "redirect:/admin/userList?currentPage="+currentPage;
    }

    @RequestMapping("/logOff")
    public String logOff(HttpSession session){

        if (session.getAttribute("user")!=null){
            session.removeAttribute("user");
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        User user = userService.login(username,password);
        if(user!=null){
            //登录成功  将user存入session
            session.setAttribute("user",user);
            return "redirect:/index.jsp";
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping("/verifyUsername")
    @ResponseBody
    public String checkUsername(String username){
        User user = userService.verifyUsername(username);
        if(user!=null){
            System.out.println(user);
            System.out.println("用户名已存在");
            return "0";
        }
        System.out.println("用户名可用");
        return "1";
    }

    @RequestMapping("/register")
    public String register(String username,String password,String email){
        userService.register(username,password,email);
        return "redirect:/login.jsp";
    }



}
