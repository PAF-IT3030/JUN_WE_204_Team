package com.post.system.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/postImageFolder/**")
                .addResourceLocations("classpath:/static/postImageFolder/")
                .setCacheControl(CacheControl.maxAge(0, TimeUnit.SECONDS));
        registry.addResourceHandler("/statusImageFolder/**")
                .addResourceLocations("classpath:/static/statusImageFolder/")
                .setCacheControl(CacheControl.maxAge(0, TimeUnit.SECONDS));
    }



    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
