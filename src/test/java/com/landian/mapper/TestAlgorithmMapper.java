package com.landian.mapper;

import com.landian.domain.Algorithm;
import com.landian.util.FileUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestAlgorithmMapper {

    @Test
    public void testFileWriter() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AlgorithmMapper mapper = sqlSession.getMapper(AlgorithmMapper.class);

        Algorithm algorithmById = mapper.downloadAlgorithm(20);
        System.out.println(algorithmById);

        String fileName = algorithmById.getFileName();
        System.out.println(fileName);
        System.out.println(fileName.contains(".docx"));//replace这个方法会有String返回值

    }

    @Test
    public void testFindAlgorithmById() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AlgorithmMapper mapper = sqlSession.getMapper(AlgorithmMapper.class);

        Algorithm algorithmById = mapper.findAlgorithmById(2);
        System.out.println(algorithmById);
    }

    @Test
    public void testFindAll() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AlgorithmMapper mapper = sqlSession.getMapper(AlgorithmMapper.class);

        List<Algorithm> allAlgorithm = mapper.findAllAlgorithm();
        for (Algorithm algorithm : allAlgorithm) {
            System.out.println(algorithm);
        }
    }

    @Test
    public void testAddFile() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AlgorithmMapper mapper = sqlSession.getMapper(AlgorithmMapper.class);

        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "行列式的性质\n" +
                "性质1  行列式与它的转置行列式相等\n" +
                "注：行列式中行与列具有同等的地位,行列式的性质凡是对行成立的对列也同样成立.\n" +
                "\n" +
                "性质2  互换行列式的两行（列）,行列式变号\n" +
                "推论  如果行列式有两行（列）完全相同，则此行列式为零";
        mapper.addAlgorithm(str,bf.format(new Date()),"2","行列式计算算法","行列式.txt");
        sqlSession.commit();
        sqlSession.close();
    }


}
