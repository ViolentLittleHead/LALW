package com.landian.test;

import com.github.pagehelper.PageHelper;
import com.landian.domain.Question;
import com.landian.mapper.QuestionMapper;
import com.landian.service.QuestionService;
import com.landian.service.impl.QuestionServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class QuestionTest {

    @Autowired
    private QuestionService questionService;


    @Test
    public void testFindQuestionById() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);


        Question questionById = mapper.findQuestionById(1);
        System.out.println(questionById);
    }

    @Test
    public void testFindAllByMost() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);

        List<Question> allByMost = mapper.findAllByMost();
        for (Question question : allByMost) {
            System.out.println(question);
        }
    }

    @Test
    public void testQuestionService() throws IOException {
        /*InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);

        //设置分页相关参数  当前页+每页显示的条数
        PageHelper.startPage(1,3);

        List<Question> all = mapper.findAll();
        for (Question question : all) {
            System.out.println(question);
        }*/

        PageHelper.startPage(2,3);
        List<Question> all = questionService.findAll(2);
        for (Question question : all) {
            System.out.println(question);
        }
    }
    @Test
    public void testFindAll() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);

        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);

        List<Question> all = mapper.findAll();
        for (Question question : all) {
            System.out.println(question);
        }
    }


}
