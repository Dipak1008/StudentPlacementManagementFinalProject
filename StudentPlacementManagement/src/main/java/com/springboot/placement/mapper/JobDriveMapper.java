package com.springboot.placement.mapper;

import org.springframework.stereotype.Component;

import com.springboot.placement.dto.JobDriveRequestDTO;
import com.springboot.placement.dto.JobDriveResponseDTO;
import com.springboot.placement.entity.Company;
import com.springboot.placement.entity.JobDrive;
import com.springboot.placement.enums.JobDriveStatus;

@Component
public class JobDriveMapper {

	public JobDrive toEntity(JobDriveRequestDTO dto, Company company) {
		
		JobDrive jobDrive = new JobDrive();
		
		jobDrive.setJobTitle(dto.getJobTitle());
		jobDrive.setDescription(dto.getDescription());
		jobDrive.setMinimumCgpa(dto.getMinimumCgpa());
		jobDrive.setRequiredbranch(dto.getRequiredBranch());
		jobDrive.setLocation(dto.getLocation());
		jobDrive.setDriveDate(dto.getDriveDate());
		jobDrive.setApplicationDeadline(dto.getApplicationDeadline());
		
		jobDrive.setStatus(JobDriveStatus.OPEN);
		
		jobDrive.setCompany(company);
		
		return jobDrive;
		
	}
	
	
	public JobDriveResponseDTO toResponse(JobDrive jobDrive) {
		
		JobDriveResponseDTO dto = new JobDriveResponseDTO();
		
		dto.setJobDriveId(jobDrive.getJobDriveId());
		dto.setJobTitle(jobDrive.getJobTitle());
		dto.setDescription(jobDrive.getDescription());
		dto.setMinimumCgpa(jobDrive.getMinimumCgpa());
		dto.setRequiredBranch(jobDrive.getRequiredbranch());
		dto.setLocation(jobDrive.getLocation());
		dto.setDriveDate(jobDrive.getDriveDate());
		dto.setApplicationDeadline(jobDrive.getApplicationDeadline());
		dto.setStatus(jobDrive.getStatus());
		
        
		if(jobDrive.getCompany() != null) {
			dto.setCompanyId(jobDrive.getCompany().getCompanyId());
			dto.setCompanyName(jobDrive.getCompany().getCompanyName());
		}
		
		return dto;
		
	}
}
