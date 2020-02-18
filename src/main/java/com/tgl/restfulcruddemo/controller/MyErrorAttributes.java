package com.tgl.restfulcruddemo.controller;


//给容器中加入我们自己定义的ErrorAttribute

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import java.util.Map;

//给容器中加入我们自定义的ErroAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes{
    //返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> map= super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company","huawei");
        //我们的异常处理器携带的数据
       Map<String,Object>ext= (Map<String,Object>)webRequest.getAttribute("ext",0);
       map.put("name","tgl");
       map.put("ext",ext);
        return map;
    }
}