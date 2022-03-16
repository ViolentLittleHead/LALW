package com.landian.service;

import com.landian.domain.Question;
import com.landian.mapper.QuestionMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestQuestionService {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testFindAllByMost(){
        List<Question> allByMost = questionService.findAllByMost(1);
        for (Question question : allByMost) {
            System.out.println(question);
        }
    }

    @Test
    public void testFindAll(){
        List<Question> allByMost = questionService.findAll(1);
        for (Question question : allByMost) {
            System.out.println(question);
        }
    }

    @Test
    public void testFindQuestion() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);

        //Question question = mapper.findQuestion(1);
        //System.out.println(question);
    }
}
