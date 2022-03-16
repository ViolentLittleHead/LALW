package com.landian.service;

import com.landian.domain.Matrix;

public interface MatrixService {
    //加法
    Matrix add(Matrix matrix1, Matrix matrix2);
    //减法
    Matrix subtract(Matrix matrix1,Matrix matrix2);
    //乘法
    Matrix multiply(Matrix matrix1,Matrix matrix2);
    //转置
    Matrix transpose(Matrix matrix);
    //求逆矩阵
    Matrix inverse(Matrix matrix);
    //求矩阵的秩
    float rank(Matrix matrix1);
    //求特征值
    float eigenValue(Matrix matrix1,Matrix matrix2);
    //判断两矩阵是否相同
    Boolean judgeSame(Matrix matrix1,Matrix matrix2);
}
