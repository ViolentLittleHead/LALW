package com.landian.controller;

import com.landian.domain.Determinant;
import com.landian.service.DeterminantService;
import com.landian.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/determinant")
public class DeterminantController {

    @Autowired
    private DeterminantService determinantService;

    @RequestMapping("/toResult")
    @ResponseBody
    public String toResult(String dtm){
        Utils utils = new Utils();
        Determinant determinant = utils.stringToArray(dtm);
        float v = determinantService.toResult(determinant.getDtm(), determinant.getOrder() - 1);
        return String.valueOf(v);
    }
}
