package com.springboot.placement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestDTO {
	
	    @NotNull(message = "Student ID is required")
	    private Long studentId;

	    @NotNull(message = "Job Drive ID is required")
	    private Long jobDriveId;
	}