package com.xmcc.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class helloController1 {
    @RequestMapping("/hello")
    public String hello(){


        return "hello gay";
    }


}
