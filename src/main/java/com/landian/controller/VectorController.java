package com.landian.controller;

import com.landian.domain.Matrix;
import com.landian.service.MatrixService;
import com.landian.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/vector")
@Controller
public class VectorController {

    @Autowired
    private MatrixService matrixService;

    Utils utils = new Utils();

    @RequestMapping("/toResult")
    @ResponseBody
    public String toResult(String vector){
        Matrix matrix = null;
        try {
            matrix = utils.stringToMatrix(vector);
        } catch (Exception e) {
            return "元素缺省";
        }
        float rank = matrixService.rank(matrix);
        int r = (int) rank;
        Integer row = matrix.getRow();
        if(r<row){
            return "向量组线性相关";
        }else {
            return "向量组线性无关";
        }
    }
}
