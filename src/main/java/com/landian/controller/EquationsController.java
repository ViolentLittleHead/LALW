package com.landian.controller;


import com.landian.domain.Equations;
import com.landian.service.EquationsService;
import com.landian.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/equations")
public class EquationsController {

    @Autowired
    private EquationsService equationsService;

    private Utils utils = new Utils();

    @RequestMapping("/toResult")
    @ResponseBody
    public String toResult(String matrixA,String matrixB){
        String s = null;
        try {
            Equations equations = utils.stringToEquations(matrixA, matrixB);
            System.out.println(matrixB);
            s = equationsService.toResult(equations);
        } catch (Exception e) {
            e.printStackTrace();
            return "矩阵中有元素缺省！";
        }
        return s;
    }

}
