package com.landian.test;

import com.landian.domain.Matrix;
import com.landian.service.MatrixService;
import com.landian.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MatrixTest {

    @Autowired
    private MatrixService matrixService;

    Utils utils = new Utils();

    //判断两矩阵是否相似
    @Test
    public void test8() throws Exception {
        String matrixA="2.0 0.0 0.0\n" +
                "0.0 0.0 1.0\n" +
                "0.0 1.0 0.0";
        String matrixB="2.0 0.0 0.0\n" +
                "0.0 3.0 4.0\n" +
                "0.0 -2.0 -3.0";
        Matrix matrix1 = utils.stringToMatrix(matrixA);
        Matrix matrix2 = utils.stringToMatrix(matrixB);
        Boolean aBoolean = matrixService.judgeSame(matrix1, matrix2);
        System.out.println(aBoolean);
    }

    //测试求秩
    @Test
    public void test7() throws Exception {
        String matrixA="2.0 -1.0 3.0\n" +
                "1.0 -3.0 4.0\n" +
                "-1.0 2.0 3.0";             //秩为3
        /*String matrixA="2.0 -1.0 3.0\n" +
                "1.0 -3.0 4.0\n" +
                "-1.0 2.0 -3.0";*/          //秩为2
        Matrix matrix = utils.stringToMatrix(matrixA);
        float rank = matrixService.rank(matrix);
        System.out.println(rank);
    }

    //测试求逆
    @Test
    public void test6() throws Exception {
        String matrixA="1.0 0.0 0.0\n" +
                "0.0 2.0 0.0\n" +
                "0.0 0.0 3.0";
        Matrix matrix = utils.stringToMatrix(matrixA);
        Matrix inverse = matrixService.inverse(matrix);
        float[][] mat = inverse.getMat();
        for (float[] floats : mat) {
            for (float aFloat : floats) {
                System.out.print(aFloat);
            }
            System.out.println();
        }
    }

    //测试转置
    @Test
    public void test5() throws Exception {
        String matrixB="1.0 2.0 3.0\n" +
                "4.0 5.0 6.0";
        Matrix matrix = utils.stringToMatrix(matrixB);
        Matrix transpose = matrixService.transpose(matrix);
        float[][] mat = transpose.getMat();
        for (float[] floats : mat) {
            for (float aFloat : floats) {
                System.out.print(aFloat);
            }
            System.out.println();
        }
    }

    //测试乘
    @Test
    public void test4() throws Exception {
        String matrixA="1.0 2.0 \n" +
                "4.0 5.0 \n" +
                "7.0 8.0 ";
        String matrixB="1.0 2.0 3.0\n" +
                "4.0 5.0 6.0";
        Matrix matrix1 = utils.stringToMatrix(matrixA);
        Matrix matrix2 = utils.stringToMatrix(matrixB);
        Matrix multiply = matrixService.multiply(matrix1, matrix2);
        float[][] mat = multiply.getMat();
        for (float[] floats : mat) {
            for (float aFloat : floats) {
                System.out.print(aFloat);
            }
            System.out.println();
        }
        System.out.println("行数："+multiply.getRow());
        System.out.println("列数："+multiply.getColumn());
    }

    //测试add
    @Test
    public void test3() throws Exception {
        String matrixA="1.0 2.0 3.0\n" +
                        "4.0 5.0 6.0\n" +
                        "7.0 8.0 9.0";
        String matrixB="1.0 2.0 3.0\n" +
                "4.0 5.0 6.0\n" +
                "7.0 8.0 9.0";
        Matrix matrix1 = utils.stringToMatrix(matrixA);
        Matrix matrix2 = utils.stringToMatrix(matrixB);
        Matrix add = matrixService.add(matrix1, matrix2);
        float[][] mat1 = add.getMat();
        for (float[] floats : mat1) {
            for (float aFloat : floats) {
                System.out.println(aFloat);
            }
        }
        //String s = utils.matrixToString(add);
        //System.out.println(s);
    }

    //字符串转化成矩阵
    @Test
    public void test2() throws Exception {
        String str = "1.0 2.0 3.0\n" +
                "4.0 5.0 6.0\n" +
                "7.0 8.0 9.0\n" +
                "5115.0 6561.0 5.0";
        Matrix matrix = utils.stringToMatrix(str);
        float[][] mat = matrix.getMat();
        for (float[] floats : mat) {
            for (float aFloat : floats) {
                System.out.println(aFloat);
            }
        }
    }

    //矩阵转换成字符串
    @Test
    public void test1(){
        float[][] d = {{1,2,3},{4,5,6},{7,8,9},{5115,6561,5}};
        Matrix matrix = new Matrix();
        matrix.setMat(d);
        matrix.setRow(4);
        matrix.setColumn(3);
        String s = utils.matrixToString(matrix);
        System.out.println(s);
    }
}
