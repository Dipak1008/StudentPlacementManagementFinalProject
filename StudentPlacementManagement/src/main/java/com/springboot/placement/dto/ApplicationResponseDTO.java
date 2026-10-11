package com.springboot.placement.dto;

import java.time.LocalDate;

import com.springboot.placement.enums.ApplicationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDTO {

	    private Long applicationId;

	    private LocalDate applicationDate;

	    private ApplicationStatus status;

	    private Long studentId;

	    private String studentName;

	    private Long jobDriveId;

	    private String jobTitle;

	    private Long companyId;

	    private String companyName;
	}
