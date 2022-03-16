package com.landian.mapper;

import com.landian.domain.Algorithm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlgorithmMapper {

    void deleteAlgorithmByUid(int uid);

    void addAlgorithm(@Param("content") String content,@Param("date") String date, @Param("uid")String uid, @Param("title")String title, @Param("fileName")String fileName);

    List<Algorithm> findAllAlgorithm();

    //只查询内容的前100个字符
    Algorithm findAlgorithmById(int id);

    //获取下载内容
    Algorithm downloadAlgorithm(int id);
}
