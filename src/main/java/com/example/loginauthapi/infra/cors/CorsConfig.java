package com.example.loginauthapi.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite todas as origens para todas as rotas
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500") // Certifique-se de que a origem está correta
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true); // Permite enviar credenciais (cookies, cabeçalhos de autenticação, etc.)
    }
}
