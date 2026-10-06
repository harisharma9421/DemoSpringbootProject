package com.springboot.student_management_system.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public String AppName(){
        return "Student Management System";
    }
}


