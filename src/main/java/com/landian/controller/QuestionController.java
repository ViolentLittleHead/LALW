package com.landian.controller;

import com.github.pagehelper.PageInfo;
import com.landian.domain.Answer;
import com.landian.domain.Question;
import com.landian.domain.User;
import com.landian.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequestMapping("/question")
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(int currentPage){
        //currentPage为当前页数
        List<Question> all = questionService.findAll(currentPage);

        PageInfo<Question> questionPageInfo = new PageInfo<>(all);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("questionList",all);
        modelAndView.addObject("choose_status","最新");
        modelAndView.addObject("status","");
        modelAndView.addObject("currentPage",questionPageInfo.getPageNum()); //当前页数
        modelAndView.addObject("total",questionPageInfo.getTotal()); //总条数
        modelAndView.addObject("pages",questionPageInfo.getPages()); //总页数
        modelAndView.setViewName("elementPages/questionList");
        return modelAndView;
    }

    @RequestMapping("/findAllByMost")
    public ModelAndView findAllByMost(int currentPage){
        //currentPage为当前页数
        List<Question> all = questionService.findAllByMost(currentPage);

        PageInfo<Question> questionPageInfo = new PageInfo<>(all);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("questionList",all);
        modelAndView.addObject("status","ByMost");
        modelAndView.addObject("choose_status","最热");
        modelAndView.addObject("currentPage",questionPageInfo.getPageNum()); //当前页数
        modelAndView.addObject("total",questionPageInfo.getTotal()); //总条数
        modelAndView.addObject("pages",questionPageInfo.getPages()); //总页数
        modelAndView.setViewName("elementPages/questionList");
        return modelAndView;
    }

    @RequestMapping("/findQuestionById")
    public ModelAndView findQuestionById(int currentPage,int id){
        Question question = questionService.findQuestionById(currentPage, id);
        PageInfo<Answer> answerPageInfo = new PageInfo<>(question.getAnswerList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("question",question);
        modelAndView.addObject("currentPage",answerPageInfo.getPageNum()); //当前页数
        modelAndView.addObject("total",answerPageInfo.getTotal()); //总条数
        modelAndView.addObject("pages",answerPageInfo.getPages()); //总页数
        modelAndView.setViewName("question");
        return modelAndView;
    }

    @RequestMapping("/put")
    public String putQuestion(String content, HttpSession session){
        Question question = new Question();
        User user = (User) session.getAttribute("user");
        question.setContent(content);
        question.setDate(new Date());
        questionService.putQuestion(question,user.getId());
        return "redirect:/pages/forum.jsp";
    }

}
