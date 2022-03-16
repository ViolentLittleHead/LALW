package com.landian.controller;

import com.landian.domain.Answer;
import com.landian.domain.User;
import com.landian.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping("/put")
    public String putAnswer(String content, int qid, HttpSession session){
        User user = (User)session.getAttribute("user");
        Answer answer = new Answer();
        answer.setqId(qid);
        answer.setContent(content);
        answer.setDate(new Date());
        answerService.putAnswer(answer,user.getId());

        return "redirect:/question/findQuestionById?id="+qid+"&currentPage=1";
    }

}
