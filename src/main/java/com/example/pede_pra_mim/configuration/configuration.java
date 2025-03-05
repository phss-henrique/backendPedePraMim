package com.example.pede_pra_mim.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class configuration {
    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")  // Frontend URL
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*");
        }
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            // Habilita a leitura de arquivos da pasta uploads
            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:/C:/Users/Pedro Henrique/Desktop/PedePraMimBackend/Mescla/pede_pra_mim/uploads/");
        }
    }

}
