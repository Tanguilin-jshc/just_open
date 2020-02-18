package com.tgl.restfulcruddemo.config;

import com.tgl.restfulcruddemo.component.LoginHandlerInterceptor;
import com.tgl.restfulcruddemo.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //浏览器发送/tgl 请求来到success
        registry.addViewController("/tgl").setViewName("success");
    }

    //所有的webMvcConfigureAdapter组件都会一起起作用
    @Bean
     public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
               // super.addViewControllers(registry);
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // super.addInterceptors(registry);
                //静态资源： *.css , *.js springboot已经做好了静态资源映射
              //  registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html"
               //         ,"/","/user/login");
            }
        };

        return adapter;
     }

     @Bean
     public WebMvcConfigurerAdapter webMvcConfigurerAdapter2(){
        WebMvcConfigurerAdapter adapter2=new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
               // super.addViewControllers(registry);
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("all");
            }

        };
        return adapter2;
     }

     @Bean
     public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
     }
}
