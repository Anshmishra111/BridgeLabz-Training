package com.contactapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI / Swagger UI configuration.
 * Accessible at: http://localhost:8080/swagger-ui.html
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Contact REST API",
                version = "1.0.0",
                description = "CRUD REST API for managing contacts — built with Spring Boot 3 & Spring Data JPA",
                contact = @Contact(
                        name = "ContactApp Team",
                        email = "support@contactapp.com"
                ),
                license = @License(name = "MIT License")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Development Server")
        }
)
@Configuration
public class OpenApiConfig {
    // SpringDoc auto-configures the rest; this class provides metadata only.
}
