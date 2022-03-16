package com.landian.controller;

import com.github.pagehelper.PageInfo;
import com.landian.domain.Algorithm;
import com.landian.domain.User;
import com.landian.service.AlgorithmService;
import com.landian.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/algorithm")
public class AlgorithmController {

    @Autowired
    private AlgorithmService algorithmService;

    @RequestMapping("/algorithmById")
    public ModelAndView algorithmById(int id){
        ModelAndView modelAndView = new ModelAndView();
        Algorithm algorithm= algorithmService.findAlgorithmById(id);
        modelAndView.setViewName("elementPages/algorithm_download");
        modelAndView.addObject("algorithm",algorithm);

        return modelAndView;
    }

    @RequestMapping("/downloadById") //匹配的是href中的download请求
    public void download(int id ,HttpServletResponse response) throws IOException{
        response.setContentType("multipart/form-data");
        Algorithm algorithm = algorithmService.downloadAlgorithm(id);
        String fileName = algorithm.getFileName();
        if(fileName.contains(".docx")){
            fileName = fileName.replace(".docx",".doc");
        }
        File file = FileUtil.StringIntoFile(algorithm.getContent(), fileName);

        System.out.println(fileName);
        response.setHeader("Content-Disposition","attchement;fileName="+new String(fileName.getBytes("utf-8"),"ISO8859-1"));
        try {
            FileInputStream in = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            int b ;
            while ((b = in.read()) != -1){
                out.write(b);
            }
            in.close();
            out.close();
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

        /*byte[] body = null;
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;*/

    }

    @RequestMapping("/algorithmList")
    public ModelAndView findAlgorithmList(int currentPage){
        List<Algorithm> algorithmList = algorithmService.findAllAlgorithm(currentPage);
        PageInfo<Algorithm> algorithmPageInfo = new PageInfo<>(algorithmList);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("algorithmList",algorithmList);
        modelAndView.addObject("currentPage",algorithmPageInfo.getPageNum()); //当前页数
        modelAndView.addObject("total",algorithmPageInfo.getTotal()); //总条数
        modelAndView.addObject("pages",algorithmPageInfo.getPages()); //总页数
        modelAndView.setViewName("algorithm_list");
        return modelAndView;
    }

    @RequestMapping("/upload")
    public String saveAlgorithmFile(String title, MultipartFile uploadFile, Model model,HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        int uid = user.getId();
        String originalFilename = uploadFile.getOriginalFilename();
        String content = "";

        try {
            content = FileUtil.multipartFileToString(uploadFile);
        }catch (InvalidOperationException e){
            session.setAttribute("result","word文档内包含图片无法读取");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(content);
        Algorithm algorithm = new Algorithm();
        algorithm.setDate(new Date());
        algorithm.setTitle(title);
        algorithm.setFileName(originalFilename);
        algorithm.setContent(content);
        if(content!=""){
            algorithmService.uploadAlgorithmFile(algorithm,uid);
            session.setAttribute("result","上传成功");
        }

        return "redirect:/algorithm/algorithmList?currentPage=1";
    }
}
