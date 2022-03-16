package com.landian.mapper;

import com.landian.domain.Question;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestQuestionMapper {

    @Test
    public void testAddQuestion() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);

        Date date = new Date();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//多态
        mapper.addQuestion("考研应该怎么准备线性代数呢？",bf.format(date),"5");

        sqlSession.commit();
        sqlSession.close();
    }
}
