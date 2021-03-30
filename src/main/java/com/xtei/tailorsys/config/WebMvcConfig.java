package com.xtei.tailorsys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * FileName: WebMvcConfig
 * Author: Li Zihao
 * Date: 2021/3/2 13:09
 * Description:
 */


@CrossOrigin(origins = "*",maxAge = 3600)
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        //设置登录处理操作
        registry.addViewController("/login").setViewName("/login");
    }

/*    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistry interceptorRegistry = registry.addInterceptor(authorizationInterceptor);
        //配置不拦截的路径
        interceptorRegistry.excludePathPatterns("/image/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        *//*file: 后面是图片在服务器上的路径*//*
        registry.addResourceHandler("/image/**").addResourceLocations("file:d:/image");
    }*/

}
