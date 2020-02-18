package com.tgl.restfulcruddemo.controller;

import com.tgl.restfulcruddemo.exception.UserNotExitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    //浏览器客户端返回的都是json
//    @ResponseBody
//    @ExceptionHandler(UserNotExitException.class)
//    public Map<String, Object> exceptionHandler(Exception e){
//        Map<String,Object> map=new HashMap<>();
//        map.put("code","user.notexit");
//        map.put("message",e.getMessage());
//        return map;
//    }


    @ExceptionHandler(UserNotExitException.class)
    public String exceptionHandler(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        //传入我们自己的错误状态码 4xx 5xx,否则就不会进入定制错误页面的解析流程
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexit");
        map.put("message",e.getMessage());
        request.setAttribute("ext",map);
        //转发到/error
        return "forward:/error";
    }
}
