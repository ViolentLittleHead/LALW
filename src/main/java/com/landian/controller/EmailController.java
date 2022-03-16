package com.landian.controller;

import com.landian.util.EmailUtil;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/email")
public class EmailController {
    private String email;  // 获取的收件人邮箱
    private String vCode;  // 后台产生的验证码
    private EmailUtil emailUtil = EmailUtil.instance;
    public EmailController() {
        // TODO Auto-generated constructor stub
        System.out.println("初始化");

    }
    @RequestMapping("/send")
    protected void sendEmail(@RequestParam("email") String email, HttpSession session) throws UnsupportedEncodingException {
        this.email = email;
        String vcode = mGetVCode();
        session.setAttribute("vcode",vcode);
    }

    /*
     * 产生验证码，并发送邮件
     */
    private String mGetVCode() {

        if(!isEmail(email)) {  // 邮箱不正确
            System.out.println("邮箱不正确");
            return "";
        }
        try {
            emailUtil.sendEmail(email);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        vCode = emailUtil.getVCode();
        System.out.println("验证码为：" + vCode);
        return vCode;
    }
    private boolean isEmail(String email) {
        if(email.length() == 0 || email == null) {
            System.out.println("邮箱长度问题");
            return false;
        }
        // 正则表达式验证邮箱
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        return pattern.matcher(email).matches();
    }
}
