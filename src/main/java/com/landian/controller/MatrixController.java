package com.landian.controller;

import com.landian.domain.Determinant;
import com.landian.domain.Matrix;
import com.landian.service.DeterminantService;
import com.landian.service.MatrixService;
import com.landian.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/matrix")
public class MatrixController {

    @Autowired
    private MatrixService matrixService;

    @Autowired
    private DeterminantService determinantService;

    private Utils utils = new Utils();

    @RequestMapping("/add")
    @ResponseBody
    public String add(String matrixA,String matrixB){
        Matrix matrix1 = null;
        Matrix matrix2 = null;
        try {
            matrix1 = utils.stringToMatrix(matrixA);
            matrix2 = utils.stringToMatrix(matrixB);
        } catch (Exception e) {
            return "矩阵中有元素缺省！";
        }
        if (matrix1.getRow()!=matrix2.getRow()||matrix1.getColumn()!=matrix2.getColumn()){
            return "两矩阵行列数不完全相同无法计算";
        }
        Matrix resultMatrix = matrixService.add(matrix1,matrix2);
        String s = utils.matrixToString(resultMatrix);
        return s;
    }

    @RequestMapping("/subtract")
    @ResponseBody
    public String subtract(String matrixA,String matrixB){
        Matrix matrix1 = null;
        Matrix matrix2 = null;
        try {
            matrix1 = utils.stringToMatrix(matrixA);
            matrix2 = utils.stringToMatrix(matrixB);
        } catch (Exception e) {
            return "矩阵中有元素缺省！";
        }
        if (matrix1.getRow()!=matrix2.getRow()||matrix1.getColumn()!=matrix2.getColumn()){
            return "两矩阵行列数不完全相同无法计算";
        }
        Matrix resultMatrix = matrixService.subtract(matrix1,matrix2);
        String s = utils.matrixToString(resultMatrix);
        return s;
    }

    @RequestMapping("/multiply")
    @ResponseBody
    public String multiply(String matrixA,String matrixB){
        Matrix matrix1 = null;
        Matrix matrix2 = null;
        try {
            matrix1 = utils.stringToMatrix(matrixA);
            matrix2 = utils.stringToMatrix(matrixB);
        } catch (Exception e) {
            return "矩阵中有元素缺省！";
        }
        if (matrix1.getColumn()!=matrix2.getRow()){
            return "矩阵A的列数不等于矩阵B的行数,无法计算";
        }
        Matrix resultMatrix = matrixService.multiply(matrix1,matrix2);
        String s = utils.matrixToString(resultMatrix);
        return s;
    }

    @RequestMapping("/transpose")
    @ResponseBody
    public String transpose(String matrixA){
        Matrix matrix1 = null;
        try {
            matrix1 = utils.stringToMatrix(matrixA);
        } catch (Exception e) {
            return "矩阵中有元素缺省！";
        }
        Matrix resultMatrix = matrixService.transpose(matrix1);
        String s = utils.matrixToString(resultMatrix);
        return s;
    }

    @RequestMapping("/inverse")
    @ResponseBody
    public String inverse(String matrixA){
        Matrix matrix1 = null;
        try {
            matrix1 = utils.stringToMatrix(matrixA);
        } catch (Exception e) {
            return "矩阵中有元素缺省！";
        }
        if (matrix1.getColumn()!=matrix1.getRow()){
            return "矩阵A不是方阵,无法求逆";
        }
        float v = determinantService.toResult(matrix1.getMat(), matrix1.getRow() - 1);
        if(v==0){
            return "矩阵行列式为零,无法求逆";
        }
        Matrix resultMatrix = matrixService.inverse(matrix1);
        String s = utils.matrixToString(resultMatrix);
        return s;
    }

    @RequestMapping("/rank")
    @ResponseBody
    public String rank(String matrixA){
        Matrix matrix1 = null;
        try {
            matrix1 = utils.stringToMatrix(matrixA);
        } catch (Exception e) {
            return "矩阵中有元素缺省！";
        }
        float resultMatrix = matrixService.rank(matrix1);
        return String.valueOf(resultMatrix);
    }

    @RequestMapping("/judgeSame")
    @ResponseBody
    public String judgeSame(String matrixA,String matrixB){
        Matrix matrix1 = null;
        Matrix matrix2 = null;
        try {
            matrix1 = utils.stringToMatrix(matrixA);
            matrix2 = utils.stringToMatrix(matrixB);
        } catch (Exception e) {
            return "矩阵中有元素缺省！";
        }
        if(matrix1.getColumn()!=matrix1.getRow() || matrix2.getColumn()!=matrix2.getRow()){
            return "矩阵不为方阵，无法判断";
        }
        if (matrix1.getColumn()!=matrix2.getRow()){
            return "矩阵A的列数不等于矩阵B的行数,无法计算";
        }
        Boolean flag = matrixService.judgeSame(matrix1, matrix2);
        if(flag){
            return "相似";
        }else {
            return "不相似";
        }
    }

}
