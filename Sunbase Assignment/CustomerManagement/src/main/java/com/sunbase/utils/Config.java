package com.sunbase.utils;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sunbase.service.ManualUserDetailService;

//import com.leucine.collegedirectory.config.JwtTokenGeneratorFilter;
//import com.leucine.collegedirectory.config.JwtTokenValidatorFilter;

@Configuration
@EnableWebSecurity
public class Config implements WebMvcConfigurer {
	
	@Autowired
    private ManualUserDetailService manualUserDetailService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(cors -> cors
                .configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
                    configuration.setAllowedMethods(Collections.singletonList("*"));
                    configuration.setAllowCredentials(true);
                    configuration.setAllowedHeaders(Collections.singletonList("*"));
                    configuration.setExposedHeaders(Collections.singletonList("Authorization"));
                    return configuration;
                }))
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/addCustomer").permitAll()
//                    .authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/sync").permitAll()
//                    .authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/updateCustomer/{id}").permitAll()
//                    .authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/customer/{id}", "/api/getCustomerList",
                    		"/api/search", "/api/getCustomerListWithPagination").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/delete/{id}").permitAll()
//                    .authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
//                    .hasRole("FACULTY_MEMBER")
                    
                    .anyRequest().authenticated())
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(new JwtTokenValidatorFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(new JwtTokenGeneratorFilter(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(manualUserDetailService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

}

