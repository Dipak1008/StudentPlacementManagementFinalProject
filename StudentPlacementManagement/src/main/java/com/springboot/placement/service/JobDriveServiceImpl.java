package com.springboot.placement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.placement.constants.PlacementConstants;
import com.springboot.placement.dto.JobDriveRequestDTO;
import com.springboot.placement.dto.JobDriveResponseDTO;
import com.springboot.placement.entity.Company;
import com.springboot.placement.entity.JobDrive;
import com.springboot.placement.enums.JobDriveStatus;
import com.springboot.placement.exception.ResourceNotFoundException;
import com.springboot.placement.mapper.JobDriveMapper;
import com.springboot.placement.repository.CompanyRepository;
import com.springboot.placement.repository.JobDriveRepository;

import jakarta.transaction.Transactional;

@Service
public class JobDriveServiceImpl implements JobDriveService{

	private static final Logger log = LoggerFactory.getLogger(JobDriveServiceImpl.class);
 
	@Autowired
	JobDriveRepository jobDriveRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	JobDriveMapper jobDriveMapper;

	
	@Override
	@Transactional
	public JobDriveResponseDTO createJobDrive(JobDriveRequestDTO dto) {
		  log.info("Creating job drive: {}", dto.getJobTitle());
	        Company company = companyRepository.findById(dto.getCompanyId())
	                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.COMPANY_NOT_FOUND + dto.getCompanyId()));

	        JobDrive jobDrive = jobDriveMapper.toEntity(dto, company);
	        JobDrive savedJobDrive = jobDriveRepository.save(jobDrive);

	        log.info("Job drive created successfully with ID: {}", savedJobDrive.getJobDriveId());
	        return jobDriveMapper.toResponse(savedJobDrive);
	    }
	
	

	@Override
	public JobDriveResponseDTO getJobDriveById(Long jobDriveId) {
		 log.info("Fetching job drive with ID: {}", jobDriveId);
	        JobDrive jobDrive = jobDriveRepository.findById(jobDriveId)
	                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.JOB_DRIVE_NOT_FOUND + jobDriveId));
	        return jobDriveMapper.toResponse(jobDrive);
	    }


	@Override
	public List<JobDriveResponseDTO> getAllJobDrives() {
		  log.info("Fetching all job drives");
	        return jobDriveRepository.findAll().stream().map(jobDriveMapper::toResponse).toList();
	    }

	
	@Override
	@Transactional
	public JobDriveResponseDTO updateJobDrive(Long jobDriveId, JobDriveRequestDTO dto) {
		log.info("Updating job drive with ID: {}", jobDriveId);
        JobDrive jobDrive = jobDriveRepository.findById(jobDriveId)
                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.JOB_DRIVE_NOT_FOUND + jobDriveId));
        
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.COMPANY_NOT_FOUND+ dto.getCompanyId()));

        jobDrive.setJobTitle(dto.getJobTitle());
        jobDrive.setDescription(dto.getDescription());
        jobDrive.setMinimumCgpa(dto.getMinimumCgpa());
        jobDrive.setRequiredbranch(dto.getRequiredBranch());
        jobDrive.setLocation(dto.getLocation());
        jobDrive.setDriveDate(dto.getDriveDate());
        jobDrive.setApplicationDeadline(dto.getApplicationDeadline());
        jobDrive.setCompany(company);

        JobDrive updatedJobDrive = jobDriveRepository.save(jobDrive);

        log.info("Job drive updated successfully");

        return jobDriveMapper.toResponse(updatedJobDrive);
    }

	
	@Override
	@Transactional
	public void deleteJobDrive(Long jobDriveId) {
		log.info("Deleting job drive with ID: {}", jobDriveId);

        if (!jobDriveRepository.existsById(jobDriveId)) {
            throw new ResourceNotFoundException(PlacementConstants.JOB_DRIVE_NOT_FOUND + jobDriveId);
        }

        jobDriveRepository.deleteById(jobDriveId);

        log.info("Job drive deleted successfully with ID: {}", jobDriveId);
    }



	@Override
	public List<JobDriveResponseDTO> getJobDrivesByStatus(JobDriveStatus status) {
		 log.info("Fetching job drives by status: {}", status);
	        return jobDriveRepository.findByStatus(status).stream()
	                .map(jobDriveMapper :: toResponse).toList();
	    }

	
	
	@Override
	public List<JobDriveResponseDTO> getJobDrivesByLocation(String location) {
		log.info("Fetching job drives by location: {}", location);
        return jobDriveRepository.findByLocation(location).stream()
                .map(jobDriveMapper :: toResponse).toList();
    }

	
	@Override
	public List<JobDriveResponseDTO> getEligibleJobDrives(Double cgpa) {
		log.info("Finding eligible job drives for CGPA: {}", cgpa);
	        return jobDriveRepository.findEligibleDrives(cgpa).stream()
	                .map(jobDriveMapper :: toResponse).toList();
	    }
	
	
	@Override
	@Transactional
	public JobDriveResponseDTO closeJobDrive(Long jobDriveId) {
		 log.info("Closing job drive with ID: {}", jobDriveId);
	     JobDrive jobDrive = jobDriveRepository.findById(jobDriveId)
	                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.JOB_DRIVE_NOT_FOUND + jobDriveId));
	             
	        jobDrive.setStatus(JobDriveStatus.CLOSED);
	        JobDrive updatedJobDrive = jobDriveRepository.save(jobDrive);
	        log.info("Job drive closed successfully with ID: {}", jobDriveId);

	        return jobDriveMapper.toResponse(updatedJobDrive);
	    }



	@Override
	public Page<JobDriveResponseDTO> getJobDrivesWithPagination(int page, int size, String sortBy, String direction) {
		   log.info("Fetching job drives with pagination");

		    Sort sort;
		    if (direction.equalsIgnoreCase("desc")) {
		        sort = Sort.by(sortBy).descending();
		    } else {
		        sort = Sort.by(sortBy).ascending();
		    }

		    PageRequest pageRequest = PageRequest.of(page, size, sort);
		    Page<JobDrive> jobDrives = jobDriveRepository.findAll(pageRequest);
		    return jobDrives.map(jobDriveMapper::toResponse);
		}

	
	}
