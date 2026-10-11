package com.springboot.placement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDriveRequestDTO {

	@NotBlank(message = "Job title is required")
	private String jobTitle;
	
	private String description;
	
	@NotNull(message = "Minimum CGPA is required")
	@PositiveOrZero(message = "Minimum CGPA cannot be nagative")
	private Double minimumCgpa;
	
	@NotBlank(message = "Reqiured branch is required")
	private String requiredBranch;
	
	@NotBlank(message = "Location is required")
	private String location;
	
	@NotNull(message = "Drive date is required")
	private LocalDate driveDate;
	
	@NotNull(message ="Application deadline is required" )
	private LocalDate applicationDeadline;
	
	@NotNull(message = "Company ID is reqiured")
	private Long companyId;
	
}
