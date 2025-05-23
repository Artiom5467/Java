package com.example.demomaven.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI powerCalculatorOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Power Calculator API")
                        .description("API для розрахунку потужності блоку живлення для комп'ютерних компонентів")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Power Calculator Team")
                                .email("support@powercalculator.com")
                                .url("https://powercalculator.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("oauth2"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new io.swagger.v3.oas.models.security.OAuthFlows()
                                        .authorizationCode(new io.swagger.v3.oas.models.security.OAuthFlow()
                                                .authorizationUrl("${spring.security.oauth2.client.provider.google.authorization-uri}")
                                                .tokenUrl("${spring.security.oauth2.client.provider.google.token-uri}")
                                                .scopes(new io.swagger.v3.oas.models.security.Scopes()
                                                        .addString("read", "Доступ для читання")
                                                        .addString("write", "Доступ для запису"))))));
    }
}