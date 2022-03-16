package com.landian.controller;

import com.github.pagehelper.PageInfo;
import com.landian.domain.Admin;
import com.landian.domain.Algorithm;
import com.landian.domain.Question;
import com.landian.domain.User;
import com.landian.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AlgorithmService algorithmService;

    @RequestMapping("/addUser")
    public String addUser(String username,String password,String email,String currentPage){
        userService.register(username,password,email);
        System.out.println(currentPage);
        return "redirect:/admin/userList?currentPage="+currentPage;
    }

    @RequestMapping("/algorithmList")
    public ModelAndView findAllAlgorithm(int currentPage){
        ModelAndView modelAndView = new ModelAndView();
        List<Algorithm> algorithmList = algorithmService.findAllAlgorithm(currentPage);
        PageInfo<Algorithm> algorithmPageInfo = new PageInfo<>(algorithmList);

        modelAndView.addObject("algorithmList",algorithmList);
        modelAndView.addObject("currentPage",algorithmPageInfo.getPageNum()); //当前页数
        modelAndView.addObject("total",algorithmPageInfo.getTotal()); //总条数
        modelAndView.addObject("pages",algorithmPageInfo.getPages()); //总页数
        modelAndView.setViewName("admin/admin_algorithmList");

        return modelAndView;
    }

    @RequestMapping("/questionList")
    public ModelAndView findAllAnswer(int currentPage){
        ModelAndView modelAndView = new ModelAndView();
        List<Question> questionList = questionService.findAll(currentPage);
        PageInfo<Question> questionPageInfo = new PageInfo<>(questionList);

        modelAndView.addObject("questionList",questionList);
        modelAndView.addObject("currentPage",questionPageInfo.getPageNum()); //当前页数
        modelAndView.addObject("total",questionPageInfo.getTotal()); //总条数
        modelAndView.addObject("pages",questionPageInfo.getPages()); //总页数
        modelAndView.setViewName("admin/admin_questionList");

        return modelAndView;
    }

    @RequestMapping("/userList")
    public ModelAndView findUserList(int currentPage){
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.findAllUser(currentPage);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);

        modelAndView.addObject("userList",userList);
        modelAndView.addObject("currentPage",userPageInfo.getPageNum()); //当前页数
        modelAndView.addObject("total",userPageInfo.getTotal()); //总条数
        modelAndView.addObject("pages",userPageInfo.getPages()); //总页数
        modelAndView.setViewName("admin/admin_userList");
        return modelAndView;
    }

    @RequestMapping("/login")
    public String login(String adminname, String password, HttpSession session){
        Admin admin = adminService.login(adminname, password);
        System.out.println(admin);
        if(admin!=null){
            session.setAttribute("admin",admin);
            return "redirect:/admin_index.jsp";
        }
        return "redirect:/admin_login.jsp";
    }
}
