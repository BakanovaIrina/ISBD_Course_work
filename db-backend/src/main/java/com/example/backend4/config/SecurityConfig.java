package com.example.backend4.config;

import com.example.backend4.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

        @Autowired
        private AuthFilter authFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                        .httpBasic().disable()
                        .csrf().disable()
                        .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/main", "/api/area")
                                .authenticated()
                                .anyRequest()
                                .permitAll())
                        .addFilterAfter(authFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
        }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");
        }

}
/*
@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

        @Autowired
        private AuthFilter authFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                        .httpBasic().disable()
                        .csrf().disable()
                        .authorizeHttpRequests(requests -> requests
                                .anyRequest()
                                .permitAll())
                        .addFilterAfter(authFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
        }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");
        }
}

 */
