package com.example.w7_miniproject_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class W7MiniProjectBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(W7MiniProjectBackEndApplication.class, args);
    }

}
