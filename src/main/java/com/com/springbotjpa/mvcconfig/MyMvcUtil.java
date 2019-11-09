package com.com.springbotjpa.mvcconfig;

import com.com.springbotjpa.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyMvcUtil extends WebMvcConfigurationSupport {
    // 发送我们的请求给服务器，根据列表内容 跳转对应的页面
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/").setViewName("login");
       registry.addViewController("/index.html").setViewName("login");
    }
    //给工程添加拦截器


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).excludePathPatterns("/","/index.html","/login").addPathPatterns("/**");
    }
}
