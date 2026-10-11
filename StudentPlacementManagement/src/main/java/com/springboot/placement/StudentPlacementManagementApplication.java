package com.springboot.placement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StudentPlacementManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentPlacementManagementApplication.class, args);
		System.out.println();
     	System.out.println("=============================================");
     	System.out.println("  STUDENT PLACEMENT MANAGEMENT SYSTEM START   ");
     	System.out.println("=============================================");
     	
 
     	
        
	}


}