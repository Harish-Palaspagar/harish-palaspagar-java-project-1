package com.xenosis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point for the Employee Management Spring Boot application.
 * This class is responsible for bootstrapping the application.
 */

@SpringBootApplication
public class EmployeeManagementApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command-line arguments passed during application startup
     */

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

}
