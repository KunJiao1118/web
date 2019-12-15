package com.jk.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HelloController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/main")
    public  String main(){
        return "main";
    }

    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        map.put("jk","jiaokun");
        return "cus";
    }

}
