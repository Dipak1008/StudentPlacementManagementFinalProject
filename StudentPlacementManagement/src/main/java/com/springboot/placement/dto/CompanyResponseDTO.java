package com.springboot.placement.dto;

import com.springboot.placement.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDTO {

	private Long companyId;
	private String companyName;
	private String email;
	private String phone;
	private String location;
	private String industry;
	private Role role;
}
