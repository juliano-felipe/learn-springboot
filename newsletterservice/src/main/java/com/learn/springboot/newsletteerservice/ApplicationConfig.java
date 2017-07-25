package com.learn.springboot.newsletteerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author felipe
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class ApplicationConfig {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }

}
