package com.xenosis.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI documentation of the Employee Management System API.
 */

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Employee Management System API",
                version = "1.0",
                description = "API documentation for the Employee Management System",
                contact = @Contact(
                        name = "Harish Palaspagar",
                        email = "harishpalaspagar4@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = @Server(url = "http://localhost:8080"),
        security = @SecurityRequirement(name = "basicAuth")
)
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class OpenApiConfig {

    /**
     * Bean for customizing OpenAPI configurations.
     *
     * @return OpenAPI instance with custom security scheme and requirements.
     */

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicAuth",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .scheme("basic")))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("basicAuth"));
    }
}

