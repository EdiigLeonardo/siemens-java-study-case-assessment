package com.siemens.selfservice.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI selfServiceOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Self-Service Portal API")
                .description("API de gestao de tickets - projeto de preparacao para entrevista Siemens")
                .version("v0.1"));
    }
}
