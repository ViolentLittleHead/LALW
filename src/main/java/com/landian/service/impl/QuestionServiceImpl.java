package com.landian.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landian.domain.Answer;
import com.landian.domain.Question;
import com.landian.mapper.AnswerMapper;
import com.landian.mapper.QuestionMapper;
import com.landian.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AllPermission;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public List<Question> findAll(int i) {
        PageHelper.startPage(i,8);
        List<Question> all = questionMapper.findAll();
        return all;
    }

    @Override
    public List<Question> findAllByMost(int i) {
        PageHelper.startPage(i,8);
        List<Question> all = questionMapper.findAllByMost();
        return all;
    }

    @Override
    public Question findQuestionById(int currentPage,int id) {
        Question question = questionMapper.findQuestionById(id);
        PageHelper.startPage(currentPage,8);
        List<Answer> answerList = answerMapper.findAnswerByQid(question.getId());
        question.setAnswerList(answerList);
        return question;
    }

    @Override
    public void putQuestion(Question question, int uid) {
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        questionMapper.addQuestion(question.getContent(),
                bf.format(question.getDate()),
                String.valueOf(uid));
    }

}
