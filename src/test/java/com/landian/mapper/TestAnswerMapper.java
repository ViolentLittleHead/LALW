package com.landian.mapper;

import com.landian.domain.Answer;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestAnswerMapper {

    @Test
    public void testFindAnswerListByQid() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AnswerMapper mapper = sqlSession.getMapper(AnswerMapper.class);

        List<Answer> answerByQid = mapper.findAnswerByQid(1);
        for (Answer answer : answerByQid) {
            System.out.println(answer);
        }
    }

    @Test
    public void testAddAnswer() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AnswerMapper mapper = sqlSession.getMapper(AnswerMapper.class);

        Date date = new Date();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//多态
        System.out.println(bf.format(date));
        mapper.addAnswer("多读书多看报",bf.format(date),"2","5");

        //Mybatis执行更新操作 提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }
}
