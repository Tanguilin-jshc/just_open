package com.tgl.restfulcruddemo.controller;

import com.tgl.restfulcruddemo.exception.UserNotExitException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Arrays;

import java.util.Map;

@Controller
public class HelloController {

//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return "index";
//    }

    @ResponseBody
    @RequestMapping("/hello")
    public String sayhello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExitException();
        }
        return  "hello";
    }

    @RequestMapping("/success")
    public  String success(Map<String,Object> map){
        map.put("hello","<h1>nihao</h1>");
        map.put("users", java.util.Arrays.asList("zhangsan","lisi","wangwu"));
        //classpath/templates/succes.html
        return "success";
    }
}
