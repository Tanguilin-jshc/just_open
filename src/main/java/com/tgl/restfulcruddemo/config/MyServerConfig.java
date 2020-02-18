package com.tgl.restfulcruddemo.config;

import com.tgl.restfulcruddemo.filter.MyFilter;
import com.tgl.restfulcruddemo.listener.MyListener;
import com.tgl.restfulcruddemo.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//关于服务器的配置都在这里
@Configuration
public class MyServerConfig {

    //注册三大组件
    @Bean
    public ServletRegistrationBean myServlet(){
        //当发/myservlet请求就会来到MyServlet()
        ServletRegistrationBean servletRegistrationBean=
                new ServletRegistrationBean(new MyServlet(),"/myservlet");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        //上面那个也有构造方法，直接设置拦截的servelt,和请求的路径
        //也可new出来再设置
        filterRegistrationBean.setFilter(new MyFilter());
        //set拦截路径的方法里面传的是个集合
       //Arrays.asList() 返回的是个集合
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myservlet"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean=new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return registrationBean;
    }
}
