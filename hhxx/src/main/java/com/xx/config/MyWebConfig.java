package com.xx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    //设置静态文件的目录
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/uploadImg/**","/img/**").addResourceLocations("file:E:/idea/nlxx/hhxx/src/main/resources/static/uploadImg/","file:E:/idea/nlxx/hhxx/src/main/resources/static/img/");
    }
}
