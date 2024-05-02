package com.project2.hct.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**") // --1
                .addResourceLocations("file:///Users/user/git/gangnampro2/src/main/resources/static/image"); //--2
    }

}