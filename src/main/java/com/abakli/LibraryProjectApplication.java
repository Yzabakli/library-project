package com.abakli;

import com.abakli.bootstrap.DataGenerator;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class LibraryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryProjectApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
