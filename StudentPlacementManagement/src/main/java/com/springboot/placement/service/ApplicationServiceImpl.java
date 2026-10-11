package com.springboot.placement.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.placement.constants.PlacementConstants;
import com.springboot.placement.dto.ApplicationRequestDTO;
import com.springboot.placement.dto.ApplicationResponseDTO;
import com.springboot.placement.dto.EligibilityResponseDTO;
import com.springboot.placement.entity.Application;
import com.springboot.placement.entity.JobDrive;
import com.springboot.placement.entity.Student;
import com.springboot.placement.enums.ApplicationStatus;
import com.springboot.placement.enums.JobDriveStatus;
import com.springboot.placement.exception.BusinessException;
import com.springboot.placement.exception.ResourceNotFoundException;
import com.springboot.placement.mapper.ApplicationMapper;
import com.springboot.placement.repository.ApplicationRepository;
import com.springboot.placement.repository.JobDriveRepository;
import com.springboot.placement.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
    StudentRepository studentRepository;
	
	@Autowired
    JobDriveRepository jobDriveRepository;
	
	@Autowired
    ApplicationMapper applicationMapper;


	@Override
	@Transactional
	public ApplicationResponseDTO applyForJob(ApplicationRequestDTO dto) {
		 log.info("Student {} applying for Job Drive {}", dto.getStudentId(), dto.getJobDriveId());

	    // 1 Find Student
		   Student student = studentRepository.findById(dto.getStudentId())
		       .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.STUDENT_NOT_FOUND + dto.getStudentId()));
         
		// 2 Find Job Drive
	        JobDrive jobDrive = jobDriveRepository.findById(dto.getJobDriveId())
	                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.JOB_DRIVE_NOT_FOUND + dto.getJobDriveId()));
   		        
	    // 3 Check Job Drive status
	        if (jobDrive.getStatus() != com.springboot.placement.enums.JobDriveStatus.OPEN) {
	            throw new BusinessException(PlacementConstants.JOB_DRIVE_CLOSED);
	        }     
	        
	        EligibilityResponseDTO eligibility = checkEligibility(dto.getStudentId(), dto.getJobDriveId());
	        if (!eligibility.isEligible()) {
	        	throw new BusinessException(eligibility.getMessage());
	        } 

	     // 4 Check duplicate application
	        boolean alreadyApplied = applicationRepository.existsByStudentStudentIdAndJobDriveJobDriveId(
	                        dto.getStudentId(), dto.getJobDriveId());

	        if (alreadyApplied) {
	            throw new BusinessException(PlacementConstants.ALREADY_APPLIED);
	        }
       
           
	        // 5 Create Application
	        Application application = new Application();
	        application.setApplicationDate(LocalDate.now());
	        application.setStatus(ApplicationStatus.APPLIED);
	        application.setStudent(student);
	        application.setJobDrive(jobDrive);

	        // 6 Save Application
	        Application savedApplication = applicationRepository.save(application);
	        log.info("Application created successfully with ID: {}", savedApplication.getApplicationId());

	        // 7 Convert Entity to Response DTO
	        return applicationMapper.toResponse(savedApplication);
	    }
 	
	

	@Override
	public ApplicationResponseDTO getApplicationById(Long applicationId) {
		 log.info("Fetching application with ID: {}", applicationId);

		 Application application = applicationRepository.findById(applicationId)
		       .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.APPLICATION_NOT_FOUND + applicationId));
   		    return applicationMapper.toResponse(application);
	}	    
	
	
	@Override
	public List<ApplicationResponseDTO> getAllApplications() {
		 log.info("Fetching all applications");
	        return applicationRepository.findAll().stream().map(applicationMapper::toResponse).toList();
	  }	

	
	@Override
	public List<ApplicationResponseDTO> getApplicationsByStudent(Long studentId) {
		 log.info("Fetching applications for student ID: {}", studentId);
		    return applicationRepository.findByStudentStudentId(studentId)
		            .stream().map(applicationMapper::toResponse).toList();
	 }	

	
	@Override
	public List<ApplicationResponseDTO> getApplicationsByJobDrive(Long jobDriveId) {
		log.info("Fetching applications for job drive ID: {}", jobDriveId);
	        return applicationRepository.findByJobDriveJobDriveId(jobDriveId)
	                .stream().map(applicationMapper::toResponse).toList();
   }

	
	@Override
	public List<ApplicationResponseDTO> getApplicationsByStatus(ApplicationStatus status) {
		 log.info("Fetching applications by status: {}", status);
          return applicationRepository.findByStatus(status)
		         .stream().map(applicationMapper :: toResponse).toList();
	}


	@Override
	@Transactional
	public ApplicationResponseDTO updateApplicationStatus(Long applicationId, ApplicationStatus status) {
		 log.info("Updating application {} status to {}", applicationId, status);

		    Application application =  applicationRepository.findById(applicationId)
		           .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.APPLICATION_NOT_FOUND + applicationId));
		    application.setStatus(status);

		    Application updatedApplication = applicationRepository.save(application);
            log.info("Application status updated successfully");
	        return applicationMapper.toResponse(updatedApplication);
	}


	@Override
	public void deleteApplication(Long applicationId) {
		log.info("Deleting application with ID: {}", applicationId);
        if (!applicationRepository.existsById(applicationId)) {
	        throw new ResourceNotFoundException(PlacementConstants.APPLICATION_NOT_FOUND + applicationId);
        }
	    applicationRepository.deleteById(applicationId);
	    log.info("Application deleted successfully with ID: {}", applicationId);
   }



	@Override
	public Page<ApplicationResponseDTO> getApplicationsWithPagination(int page, int size, String sortBy,
			String direction) {
	 log.info("Fetching applications with pagination");

		 Sort sort;
		    if (direction.equalsIgnoreCase("desc")) {
		        sort = Sort.by(sortBy).descending();
		    } else {
		        sort = Sort.by(sortBy).ascending();
		    }

		 PageRequest pageRequest = PageRequest.of(page, size, sort);
		 Page<Application> applications = applicationRepository.findAll(pageRequest);
		   return applications.map(applicationMapper::toResponse);
		
	}



	@Override
	public EligibilityResponseDTO checkEligibility(Long studentId, Long jobDriveId) {
		 Student s = studentRepository.findById(studentId)
		            .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.STUDENT_NOT_FOUND + studentId));

		    JobDrive j = jobDriveRepository.findById(jobDriveId)
		            .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.JOB_DRIVE_NOT_FOUND + jobDriveId));

		    EligibilityResponseDTO r = new EligibilityResponseDTO();
		    r.setStudentId(studentId);
		    r.setJobDriveId(jobDriveId);

		    boolean cgpaOk = s.getCgpa() != null && j.getMinimumCgpa() != null && s.getCgpa() >= j.getMinimumCgpa();

		    boolean branchOk = j.getRequiredbranch() == null || j.getRequiredbranch().isBlank()
		            || (s.getBranch() != null && s.getBranch().trim().equalsIgnoreCase(j.getRequiredbranch().trim()));

		    boolean statusOk = j.getStatus() == JobDriveStatus.OPEN;
		    boolean deadlineOk = j.getApplicationDeadline() == null || !java.time.LocalDate.now().isAfter(j.getApplicationDeadline());
		    boolean eligible = cgpaOk && branchOk && statusOk && deadlineOk;

		    r.setEligible(eligible);
		    r.setMessage(eligible ? "Eligible" : "Not eligible");
		    
		    return r;
		}

}
