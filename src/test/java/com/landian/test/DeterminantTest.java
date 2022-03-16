package com.landian.test;

import com.landian.service.DeterminantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DeterminantTest {

    @Autowired
     private DeterminantService determinantService;

    @Test
    public void test(){
        float[][] TestMatrix = {
                {3,2},
                {2,3}};
        float v = determinantService.toResult(TestMatrix, 1);
        System.out.println(v);
    }

    @Test
    public void test1(){
        String str = "1 2 3\n"+
                "4  5 6\n"+
                "7 8 9";
        String[] split = str.split("\n");
        /*for (String s : split) {
            System.out.println(s);
        }*/
        String[] s = split[1].split("\\s+");
        for (String s1 : s) {
            System.out.println(s1);
        }
    }
}
