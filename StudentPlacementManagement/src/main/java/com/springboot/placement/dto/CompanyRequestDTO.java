package com.springboot.placement.dto;

import com.springboot.placement.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {

	@NotBlank(message = "Company name is required")
	private String companyName;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Enter valid email")
	private String email;
	
	@NotBlank(message = "phone is required")
	private String phone;
	
	@NotBlank(message = "Location is required")
	private String location;
	
	@NotBlank(message = "Industry is required")
	private String industry;
	
	private Role role;
}
