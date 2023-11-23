package com.projectss.theUltimateTodo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:5173","https://k28951c68ade3a.user-app.krampoline.com")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }


}

