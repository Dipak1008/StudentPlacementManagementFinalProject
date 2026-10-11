package com.springboot.placement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	            	    .requestMatchers("/actuator/**").permitAll()
	            	    .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**",
                        	    "/api-docs.yaml"
                        	).permitAll()
	            	    .requestMatchers("/api/students/**").hasAnyRole("ADMIN", "STUDENT")
	            	    .requestMatchers("/api/companies/**").hasAnyRole("ADMIN", "COMPANY")
                        .requestMatchers("/api/job-drives/**").hasAnyRole("ADMIN", "STUDENT", "COMPANY")
                        .requestMatchers("/api/applications/**").hasAnyRole("ADMIN", "STUDENT", "COMPANY")
                        .requestMatchers("/api/interviews/**").hasAnyRole("ADMIN", "STUDENT", "COMPANY")
                        .requestMatchers("/api/offers/**").hasAnyRole("ADMIN", "STUDENT", "COMPANY")
                        .requestMatchers("/api/eligibility/**").permitAll()
                        .requestMatchers("/api/users/register").permitAll()
                        .anyRequest().hasRole("ADMIN")

	            	    )
	            .exceptionHandling(exception -> exception
	            		.authenticationEntryPoint((request, response, ex) -> {
	            	        response.setStatus(401);
	            	        response.setContentType("application/json");
	            	        response.getWriter().write(
	            	            "{\"status\":401,\"message\":\"Unauthorized: Please provide valid credentials\"}"
	            	        );
	            	    })
	                    .accessDeniedHandler((request, response, ex) -> {
	                        response.setStatus(403);
	                        response.setContentType("application/json");
	                        response.getWriter().write(
	                            "{\"status\":403,\"message\":\"Access Denied: You do not have permission\"}"
	                        );
	                    }))
	            .httpBasic(Customizer.withDefaults());

           return http.build();
	    }
  
  
	  @Bean
	  public PasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	  
	 }
	 
	}

