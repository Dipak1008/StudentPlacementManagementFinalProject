package com.springboot.placement.dto;

import java.time.LocalDate;

import com.springboot.placement.enums.JobDriveStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDriveResponseDTO {

	private Long jobDriveId;
    private String jobTitle;
    private String description;
    private Double minimumCgpa;
    private String requiredBranch;
    private String location;
    private LocalDate driveDate;
    private LocalDate applicationDeadline;
    private JobDriveStatus status;
    private Long companyId;
    private String companyName;
}