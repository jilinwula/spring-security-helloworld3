package com.jilinwula.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {
    @GetMapping("/helloworld")
    public Object helloworld(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", "Hello World Spring Security");
        result.put("authentication", request.getAttribute("authentication"));
        return result;
    }
}
