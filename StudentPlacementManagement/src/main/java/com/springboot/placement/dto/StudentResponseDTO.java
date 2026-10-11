package com.springboot.placement.dto;

import com.springboot.placement.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

	private Long studentId;
	private String name;
	private String email;
	private String phone;
	private String collegeName;
	private String degree;
	private String branch;
	private Double cgpa;
	private Integer graduationYear;
	private Role role;
}
