package com.landian.service;

import com.landian.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findAll(int i);

    List<Question> findAllByMost(int i);

    Question findQuestionById(int currentPage,int id);

    void putQuestion(Question question, int uid);
}
