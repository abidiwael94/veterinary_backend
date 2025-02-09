package com.example.spring_security_jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Autoriser les requêtes venant de http://localhost:4200 (Angular)
        registry.addMapping("/**")  // Autoriser toutes les routes
                .allowedOrigins("http://localhost:4200")  // Autoriser cette origine
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Autoriser ces méthodes HTTP
                .allowCredentials(true)  // Autoriser les cookies et l'authentification
                .allowedHeaders("*")  // Autoriser tous les en-têtes
                .exposedHeaders("Authorization");  // Vous pouvez également exposer d'autres en-têtes comme "Authorization" si nécessaire
    }
}