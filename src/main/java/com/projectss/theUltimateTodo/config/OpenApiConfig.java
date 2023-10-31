package com.projectss.theUltimateTodo.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version("v1.0.0")
                .title("Project 6s - EasyLog Project")
                .description("this APIs are for Project 6s - EasyLog Project ");

        return new OpenAPI()
                .info(info);
    }
}