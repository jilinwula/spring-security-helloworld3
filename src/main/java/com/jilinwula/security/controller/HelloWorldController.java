package com.jilinwula.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {
    @GetMapping("/helloworld")
    public Object helloworld() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", "Hello World Spring Security");
        return result;
    }
}
