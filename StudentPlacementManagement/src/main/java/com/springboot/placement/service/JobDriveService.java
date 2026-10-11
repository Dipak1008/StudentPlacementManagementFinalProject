package com.springboot.placement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.placement.dto.JobDriveRequestDTO;
import com.springboot.placement.dto.JobDriveResponseDTO;
import com.springboot.placement.enums.JobDriveStatus;

public interface JobDriveService {

	JobDriveResponseDTO createJobDrive(JobDriveRequestDTO dto);

    JobDriveResponseDTO getJobDriveById(Long jobDriveId);

    List<JobDriveResponseDTO> getAllJobDrives();

    JobDriveResponseDTO updateJobDrive(Long jobDriveId, JobDriveRequestDTO dto);

    void deleteJobDrive(Long jobDriveId);

    List<JobDriveResponseDTO> getJobDrivesByStatus(JobDriveStatus status);

    List<JobDriveResponseDTO> getJobDrivesByLocation(String location);

    List<JobDriveResponseDTO> getEligibleJobDrives(Double cgpa);

    JobDriveResponseDTO closeJobDrive(Long jobDriveId);

    Page<JobDriveResponseDTO> getJobDrivesWithPagination(int page, int size, String sortBy, String direction);
}
