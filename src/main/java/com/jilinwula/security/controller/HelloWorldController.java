package com.jilinwula.security.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {

    @Autowired
    private ShearCaptcha shearCaptcha;

    @GetMapping("/helloworld")
    public Object helloworld(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", "Hello World Spring Security");
        result.put("authentication", request.getAttribute("authentication"));
        return result;
    }

    @GetMapping("code/image")
    public void codeImage(HttpServletResponse response) throws IOException {
        shearCaptcha.createCode();
        shearCaptcha.write(response.getOutputStream());
    }
}
