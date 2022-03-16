package com.landian.util;

import com.landian.domain.Determinant;
import com.landian.domain.Equations;
import com.landian.domain.Matrix;

public class Utils {

    /**
     * 字符串转换成行列式 row行   column列
     * 1 2 3
     * 4 5 6        ==> dtm[3][3]
     * 7 8 9
     *
     * 返回行列式
     */
    public Determinant stringToArray(String str){
        String[] str1 = str.split("\n");
        int row = str1.length;
        int column = str1[0].split("\\s+").length;
        float[][] dtm = new float[column][row];
        for(int i=0;i<row;i++){
            String[] str2 = str1[i].split("\\s+");
            for (int j =0 ;j<column;j++){
                dtm[i][j] = Float.valueOf(str2[j]);
            }
        }

        Determinant determinant = new Determinant();
        determinant.setDtm(dtm);
        determinant.setOrder(row);
        return determinant;
    }

    /**
     * 字符串转化成矩阵的形式
     * 1 2 3
     * 4 5 6        ==> dtm[3][3]
     * 7 8 9
     * @param str
     * @return
     */
    public Matrix stringToMatrix(String str) throws Exception{
        String[] str1 = str.split("\n");
        int row = str1.length;
        int column = str1[0].split("\\s+").length;
        float[][] dtm = new float[row][column];
        for(int i=0;i<row;i++){
            String[] str2 = str1[i].split("\\s+");
            if(str2.length!=column){
                throw new Exception();
            }
            for (int j =0 ;j<column;j++){
                dtm[i][j] = Float.valueOf(str2[j]);
            }
        }

        Matrix matrix = new Matrix();
        matrix.setMat(dtm);
        matrix.setRow(row);
        matrix.setColumn(column);

        return matrix;
    }

    /**
     * 矩阵转化成字符串
     *                      1 2 3
     * dtm[3][3]   ==>      4 5 6
     *                      7 8 9
     * @param matrix
     * @return
     */
    public String matrixToString(Matrix matrix){
        String str = "";
        float[][] mat = matrix.getMat();
        int row = matrix.getRow();
        int colunm = matrix.getColumn();
        for (int i =0;i<row;i++){
            for(int j = 0 ;j<colunm;j++){
                if(j==0){
                    str=str+String.valueOf(mat[i][j]);
                }else {
                    str=str+" "+String.valueOf(mat[i][j]);
                }
            }
            str=str+"\n";
        }
        return str;
    }

    /**
     * 字符串转换成方程组
     * @param matrixA
     * @param matrixB
     * @return
     * @throws Exception
     */
    public Equations stringToEquations(String matrixA,String matrixB) throws Exception{
        String[] str1 = matrixA.split("\n");
        Equations equations = new Equations();
        int row = str1.length;
        int column = str1[0].split("\\s+").length;
        float[][] dtm = new float[row][column];
        for(int i=0;i<row;i++){
            String[] s1 = str1[i].split("\\s+");
            if(s1.length!=column){
                throw new Exception();
            }
            for (int j =0 ;j<column;j++){
                dtm[i][j] = Float.valueOf(s1[j]);
            }
        }
        equations.setA(dtm);
        equations.setArow(row);
        equations.setAcolumn(column);
        String[] str2 = matrixB.split("\n");
        int row1 = str2.length;
        float[] dtm1 = new float[row1];
        for(int i=0;i<row1;i++){
            String[] s2 = str2[i].split("\\s+");
            dtm1[i] = Float.valueOf(s2[0]);
            System.out.println("s2:"+s2[0]);
        }
        equations.setB(dtm1);
        equations.setBrow(row1);

        return  equations;
    }


}
