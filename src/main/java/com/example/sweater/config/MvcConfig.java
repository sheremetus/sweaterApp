package com.example.sweater.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Тут раздаются шаблоны без логики
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    public void addViewControllers(ViewControllerRegistry registry) {
        // Тут раздаются шаблоны без логики
        registry.addViewController("/login").setViewName("login");
    }

//    file: <- указывает что протокол файл то есть место где-то в файловой системе

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + uploadPath + "/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}

