package com.greetings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Spring Boot Main Entry Point.
 * Extending SpringBootServletInitializer allows it to run inside external Tomcat containers as a WAR.
 */
@SpringBootApplication
public class GreetingsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GreetingsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GreetingsApplication.class, args);
    }
}
