package com.landian.service.impl;

import com.landian.domain.Answer;
import com.landian.domain.User;
import com.landian.mapper.AnswerMapper;
import com.landian.mapper.UserMapper;
import com.landian.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("AnswerService")
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;


    @Override
    public void putAnswer(Answer answer,int uid) {
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        answerMapper.addAnswer(answer.getContent(),
                bf.format(answer.getDate()),
                String.valueOf(uid),
                String.valueOf(answer.getqId()));
    }
}
