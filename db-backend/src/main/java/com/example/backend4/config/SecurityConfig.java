package com.example.backend4.config;

import com.example.backend4.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.example.backend4")
public class SecurityConfig implements WebMvcConfigurer {

        @Autowired
        private AuthFilter authFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http.cors().and().csrf().disable()
                        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                        .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/api/auth")
                                .permitAll()
                                .requestMatchers("/api/santa/")
                                .hasAuthority("SANTA")
                                .requestMatchers("api/elf/")
                                .hasAuthority("ELF")
                                .requestMatchers("/api/shared/")
                                .authenticated()
                                .anyRequest().permitAll())
                        .build();
        }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");
        }
}

