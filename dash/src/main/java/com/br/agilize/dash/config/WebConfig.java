package com.br.agilize.dash.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("https://dash.elasticcode.com.br")  // Apenas permitir esta origem
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
            .allowedHeaders("*")  // Cabeçalhos permitidos
            .allowCredentials(true);  // Permitir envio de credenciais (cookies, headers de autorização)
  }
}
