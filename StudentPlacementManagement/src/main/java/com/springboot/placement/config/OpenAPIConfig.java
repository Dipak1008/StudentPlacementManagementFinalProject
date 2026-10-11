package com.springboot.placement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfig {
 
	 @Bean
	    public OpenAPI placementOpenAPI() {
		    
		 return new OpenAPI()
			        .info(new Info()
			                .title("Student Placement Management System API")
			                .description("REST APIs for managing students, companies, job drives, applications, interviews and offers")
			                .version("1.0")
			                .contact(new Contact()
									.name("Deepak Limbikai")
									.email("deepak@example.com")))
			
			        .addSecurityItem(
			                new SecurityRequirement().addList("basicAuth"))
			        .components(
			                new Components()
			                        .addSecuritySchemes(
			                                "basicAuth",
			                                new SecurityScheme()
			                                        .name("basicAuth")
			                                        .type(SecurityScheme.Type.HTTP)
			                                        .scheme("basic")));
	 }

}          