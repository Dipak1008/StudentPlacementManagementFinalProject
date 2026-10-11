package com.springboot.placement.dto;

import com.springboot.placement.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

	@NotBlank(message = "Student name is required")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Enter valid email")
	private String email;
	
	@NotBlank(message = "Phone is required")
	private String phone;
	
	@NotBlank(message = "College name is required")
	private String collegeName;
	
	@NotBlank(message = "Degree is required")
	private String degree;
	
	@NotBlank(message = "Branch is required")
	private String branch;
	
	@Min(value = 0, message = "CGPA cannot be negative")
	@Max(value = 10, message = "CGPA cannot be greater than 10")
	private Double cgpa;
	
	private Integer graduationYear;
	
	private Role role;
}
