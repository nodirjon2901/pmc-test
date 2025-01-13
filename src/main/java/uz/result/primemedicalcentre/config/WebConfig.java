package uz.result.primemedicalcentre.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Barcha endpointlar uchun CORS ruxsati
                .allowedOrigins("http://localhost:3000", "https://*.vercel.app") // Ruxsat etilgan domenlar
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Ruxsat etilgan HTTP metodlari
                .allowedHeaders("*") // Har qanday sarlavhalar ruxsat etiladi
                .allowCredentials(true) // Cookie va autentifikatsiya uchun
                .maxAge(3600); // Keshlash muddati (soniyalarda)
    }

}
