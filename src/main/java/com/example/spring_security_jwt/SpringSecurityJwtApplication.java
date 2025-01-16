package com.example.spring_security_jwt;

import com.example.spring_security_jwt.models.ERole;
import com.example.spring_security_jwt.models.Role;
import com.example.spring_security_jwt.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository) {
        return args -> {
            // Check if the roles already exist, and insert if not
            if (roleRepository.findByName(ERole.ROLE_CLIENT).isEmpty()) {
                roleRepository.save(new Role(ERole.ROLE_CLIENT));
            }
            if (roleRepository.findByName(ERole.ROLE_DOC).isEmpty()) {
                roleRepository.save(new Role(ERole.ROLE_DOC));
            }
            if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
                roleRepository.save(new Role(ERole.ROLE_ADMIN));
            }

            System.out.println("Roles inserted if they didn't already exist.");
        };
    }
}
