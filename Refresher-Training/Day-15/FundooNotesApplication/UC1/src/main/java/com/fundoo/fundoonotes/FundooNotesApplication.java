package com.fundoo.fundoonotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FundooNotesApplication — Entry Point
 *
 * Use Case 1: Project & Database Setup
 * Bootstraps Spring Boot with embedded Tomcat and Hibernate auto-DDL.
 */
@SpringBootApplication
public class FundooNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundooNotesApplication.class, args);
    }
}
