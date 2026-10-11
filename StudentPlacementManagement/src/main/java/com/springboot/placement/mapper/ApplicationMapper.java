package com.springboot.placement.mapper;

import org.springframework.stereotype.Component;

import com.springboot.placement.dto.ApplicationResponseDTO;
import com.springboot.placement.entity.Application;

@Component
public class ApplicationMapper {

	 public ApplicationResponseDTO toResponse(Application application) {

	        ApplicationResponseDTO dto = new ApplicationResponseDTO();

	        dto.setApplicationId(application.getApplicationId());
	        dto.setApplicationDate(application.getApplicationDate());
	        dto.setStatus(application.getStatus());

	        if (application.getStudent() != null) {
	            dto.setStudentId(application.getStudent().getStudentId());
	            dto.setStudentName(application.getStudent().getName());
	        }

	        if (application.getJobDrive() != null) {
	            dto.setJobDriveId(application.getJobDrive().getJobDriveId());
	            dto.setJobTitle(application.getJobDrive().getJobTitle());

	            if (application.getJobDrive().getCompany() != null) {
	                dto.setCompanyId(application.getJobDrive().getCompany().getCompanyId());
	                dto.setCompanyName(application.getJobDrive().getCompany().getCompanyName());
	            }
	        }
	        return dto;
	    }
	}

