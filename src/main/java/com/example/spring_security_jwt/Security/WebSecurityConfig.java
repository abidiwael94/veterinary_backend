package com.example.spring_security_jwt.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

    // Configuration CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Applique CORS sur toutes les routes
                .allowedOrigins("http://localhost:4200") // Permet les requêtes depuis http://localhost:4200 (Angular)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Autoriser les méthodes HTTP spécifiques
                .allowCredentials(true) // Autorise les cookies et l'authentification
                .allowedHeaders("*"); // Autorise tous les en-têtes
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll() // Autoriser l'accès aux API de login et signup
                .requestMatchers("/css/**", "/js/**").permitAll() // Autoriser l'accès aux assets publics (CSS, JS)
                .anyRequest().authenticated() // Protéger les autres routes
                .and()
                .formLogin()
                .loginPage("/login") // Page de connexion personnalisée
                .defaultSuccessUrl("/dashboard", true) // Rediriger après une connexion réussie
                .failureUrl("/login?error=true") // Rediriger en cas d'échec de la connexion
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login") // Rediriger après la déconnexion
                .permitAll();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
