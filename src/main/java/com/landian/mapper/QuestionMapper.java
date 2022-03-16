package com.landian.mapper;

import com.landian.domain.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {

    void deleteQuestionByUid(int uid);

    List<Question> findAll();

    List<Question> findAllByMost();

    //该方法返回的Question内部会封装一个List<Answer>
    Question findQuestionById(int id);

    void addQuestion(@Param("content")String content, @Param("date")String date, @Param("uid")String uid);
}
