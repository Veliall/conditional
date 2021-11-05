package com.example.conditional;

import com.example.conditional.profiles.DevProfile;
import com.example.conditional.profiles.ProductionProfile;
import com.example.conditional.profiles.SystemProfile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConditionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConditionalApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile", havingValue = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile", havingValue = "prod")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
