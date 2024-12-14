package com.example.retoCodificacion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;

@Configuration
public class SwaggerConfig {
    @PreAuthorize("permitAll")
    @Bean
    OpenAPI openAPI(@Value("0.0.1-SNAPSHOT")  String appVersion) {
        return new OpenAPI()
                .info(new Info().title("spring security jwt authentication and authorization")
                        .version(appVersion)
                        .description("Spring Security JWT Authentication and Authorization")
                        .contact(new Contact().email("nicooo.com").name("nico").url("")));






    }}