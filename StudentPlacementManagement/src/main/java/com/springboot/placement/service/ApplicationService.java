package com.springboot.placement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.placement.dto.ApplicationRequestDTO;
import com.springboot.placement.dto.ApplicationResponseDTO;
import com.springboot.placement.dto.EligibilityResponseDTO;
import com.springboot.placement.enums.ApplicationStatus;

public interface ApplicationService {

	ApplicationResponseDTO applyForJob(ApplicationRequestDTO dto);

    ApplicationResponseDTO getApplicationById(Long applicationId);

    List<ApplicationResponseDTO> getAllApplications();

    List<ApplicationResponseDTO> getApplicationsByStudent(Long studentId);

    List<ApplicationResponseDTO> getApplicationsByJobDrive(Long jobDriveId);

    List<ApplicationResponseDTO> getApplicationsByStatus(ApplicationStatus status);

    ApplicationResponseDTO updateApplicationStatus(Long applicationId, ApplicationStatus status);

    void deleteApplication(Long applicationId);
    
    Page<ApplicationResponseDTO> getApplicationsWithPagination(int page, int size, String sortBy, String direction);

    EligibilityResponseDTO checkEligibility(Long studentId, Long jobDriveId);
}
