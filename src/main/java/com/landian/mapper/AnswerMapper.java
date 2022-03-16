package com.landian.mapper;

import com.landian.domain.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerMapper {

    void deleteAnswerByUid(int uid);

    List<Answer> findAnswerByQid(int qid);

    void addAnswer(@Param("content")String content, @Param("date")String date, @Param("uid")String uid, @Param("qid")String qid);
}
