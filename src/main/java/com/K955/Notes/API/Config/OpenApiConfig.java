package com.K955.Notes.API.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Notes API")
                                .description(
                                        "A Spring Boot REST API for managing notes with JWT authentication, search, sorting, pagination, pin/unpin, archive/restore and soft delete."
                                )
                                .version("1.0.0")
                );
    }

}
