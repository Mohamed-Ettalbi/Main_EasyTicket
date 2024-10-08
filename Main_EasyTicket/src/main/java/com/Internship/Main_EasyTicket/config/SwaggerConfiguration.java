package com.Internship.Main_EasyTicket.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class SwaggerConfiguration {

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Main EasyTicket User Authentication API")
                            .version("0.0.1")
                            .description("API documentation for the Authenticaiton service for EasyTicket application"));
        }
    }

